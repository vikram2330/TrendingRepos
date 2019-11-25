package com.vikram.trendingrepos.data.services

import com.vikram.trendingrepos.data.model.TrendingRepositoryResponse
import io.reactivex.Single
import retrofit2.http.GET

interface ApiService {
    @GET("/repositories")
    fun getTrendingRepositories(): Single<List<TrendingRepositoryResponse>>
}