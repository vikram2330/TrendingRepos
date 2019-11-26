package com.vikram.trendingrepos.di.modules

import android.content.Context
import com.accuweather.skyguard.injection.qualifiers.ApplicationContext
import com.vikram.trendingrepos.data.services.ApiService
import com.vikram.trendingrepos.interceptors.NetworkResponseInterceptors
import com.vikram.trendingrepos.utils.AppConstants
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    internal fun provideOkHttpClient(@ApplicationContext context: Context): OkHttpClient {
        val cacheSize: Long = 10 * 1024 * 1024 //10mb
        val httpBuilder = OkHttpClient.Builder()
        httpBuilder.cache(Cache(context.cacheDir, cacheSize))
        httpBuilder.addInterceptor(NetworkResponseInterceptors())
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
    internal fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
}