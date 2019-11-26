package com.vikram.trendingrepos.data.repository.remote

import com.vikram.trendingrepos.data.model.TrendingRepositoryResponse
import com.vikram.trendingrepos.data.services.ApiService
import io.reactivex.Single
import javax.inject.Inject

class RemoteDataProviderImpl @Inject constructor(private val apiService: ApiService) :
    RemoteDataProvider {

    override fun getTrendingRepositories(): Single<List<TrendingRepositoryResponse>> {
        return apiService.getTrendingRepositories()
    }
}