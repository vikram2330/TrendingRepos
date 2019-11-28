package com.vikram.trendingrepos.ui.homescreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.vikram.trendingrepos.data.model.NetworkResponse
import com.vikram.trendingrepos.data.model.SortType
import com.vikram.trendingrepos.data.repository.Repository
import com.vikram.trendingrepos.ui.base.BaseViewModel
import com.vikram.trendingrepos.utils.IRxSchedulers
import com.vikram.trendingrepos.utils.toRepositoryItemList
import io.reactivex.Single
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val repository: Repository,
    private val iRxSchedulers: IRxSchedulers
) : BaseViewModel() {

    private val responseLiveData: MutableLiveData<NetworkResponse<List<RepositoryItem>>> =
        MutableLiveData()

    fun getResponseLiveData(): LiveData<NetworkResponse<List<RepositoryItem>>> = responseLiveData

    fun getRepositories() {
        val disposable = repository.getTrendingRepositories().subscribeOn(iRxSchedulers.io())
            .observeOn(iRxSchedulers.main()).doOnSubscribe {
                responseLiveData.value = NetworkResponse.Loading
            }.subscribe({ repositories ->
                responseLiveData.value =
                    NetworkResponse.Success(repositories.toRepositoryItemList())
            }, { error ->
                responseLiveData.value = NetworkResponse.ResponseError(error)
            })
        addDisposable(disposable)
    }

    /**
     * this method will ignore cache and fetch new results from server
     *
     */
    fun forceRefreshRepositories() {
        val disposable =
            repository.getTrendingRepositoriesForceRefresh().subscribeOn(iRxSchedulers.io())
                .observeOn(iRxSchedulers.main()).doOnSubscribe {
                    responseLiveData.value = NetworkResponse.Loading
                }.subscribe({ repositories ->
                    responseLiveData.value =
                        NetworkResponse.Success(repositories.toRepositoryItemList())
                }, { error ->
                    responseLiveData.value = NetworkResponse.ResponseError(error)
                })
        addDisposable(disposable)
    }

    fun sortRepoList(sortType: SortType) {
        if (responseLiveData.value is NetworkResponse.Success) {
            val list =
                (responseLiveData.value as NetworkResponse.Success<List<RepositoryItem>>).data
            when (sortType) {
                is SortType.SortByName -> {
                    sortByNames(Single.just(list))
                }
                is SortType.SortByStars -> {
                    sortByStars(Single.just(list))
                }
            }
        }
    }

    private fun sortByStars(single: Single<List<RepositoryItem>>) {
        addDisposable(single.map {
            val mutableList = it.toMutableList()
            mutableList.sortedBy { it.stars }
        }.subscribeOn(iRxSchedulers.io())
            .observeOn(iRxSchedulers.main())
            .doOnSubscribe {
                responseLiveData.value = NetworkResponse.Loading
            }.subscribe({ repositories ->
                responseLiveData.value = NetworkResponse.Success(repositories)
            }, { error ->
                responseLiveData.value = NetworkResponse.ResponseError(error)
            })
        )
    }

    private fun sortByNames(single: Single<List<RepositoryItem>>) {
        addDisposable(single.map {
            val mutableList = it.toMutableList()
            mutableList.sortedBy { it.repoName }
        }.subscribeOn(iRxSchedulers.io())
            .observeOn(iRxSchedulers.main())
            .doOnSubscribe {
                responseLiveData.value = NetworkResponse.Loading
            }.subscribe({ repositories ->
                responseLiveData.value = NetworkResponse.Success(repositories)
            }, { error ->
                responseLiveData.value = NetworkResponse.ResponseError(error)
            })
        )
    }

}

