package com.vikram.trendingrepos.interceptors

import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Response
import java.util.concurrent.TimeUnit

class NetworkResponseInterceptors() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val cacheControl = CacheControl.Builder().maxStale(2, TimeUnit.HOURS).build()
        val request = chain.request().newBuilder().cacheControl(cacheControl).build()
        return chain.proceed(request)
    }
}