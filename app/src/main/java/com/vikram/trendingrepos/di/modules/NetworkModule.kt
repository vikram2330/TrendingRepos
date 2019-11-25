package com.vikram.trendingrepos.di.modules

import com.vikram.trendingrepos.data.services.ApiService
import com.vikram.trendingrepos.utils.AppConstants
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    internal fun provideOkHttpClient(): OkHttpClient {
        val httpBuilder = OkHttpClient.Builder()
        return httpBuilder.build()
    }

    @Provides
    @Singleton
    internal fun provideRestAdapter(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(AppConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    internal fun provideApiService(retrofit: Retrofit):ApiService{
       return retrofit.create(ApiService::class.java)
    }
}