package com.vikram.trendingrepos.data.repository

import com.vikram.trendingrepos.data.model.TrendingRepositoryResponse
import io.reactivex.Single

interface Repository {
    fun getTrendingRepositories(): Single<List<TrendingRepositoryResponse>>
}