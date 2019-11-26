package com.vikram.trendingrepos.data.repository.remote

import com.vikram.trendingrepos.data.model.TrendingRepositoryResponse
import io.reactivex.Single

interface RemoteDataProvider {
    fun getTrendingRepositories(): Single<List<TrendingRepositoryResponse>>
}