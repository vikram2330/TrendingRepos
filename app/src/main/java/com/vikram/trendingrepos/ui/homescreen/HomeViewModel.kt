package com.vikram.trendingrepos.ui.homescreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.vikram.trendingrepos.data.model.NetworkResponse
import com.vikram.trendingrepos.data.repository.Repository
import com.vikram.trendingrepos.ui.base.BaseViewModel
import com.vikram.trendingrepos.utils.IRxSchedulers
import com.vikram.trendingrepos.utils.toRepositoryItemList
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


}