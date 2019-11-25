package com.vikram.trendingrepos.data.repository

import com.vikram.trendingrepos.data.model.TrendingRepositoryResponse
import com.vikram.trendingrepos.data.repository.remote.RemoteDataProvider
import io.reactivex.Single
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val remoteDataProvider: RemoteDataProvider) :
    Repository {
    override fun getTrendingRepositories(): Single<List<TrendingRepositoryResponse>> {
    return  remoteDataProvider.getTrendingRepositories()
    }
}