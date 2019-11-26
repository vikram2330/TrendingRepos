package com.vikram.trendingrepos.di.modules

import com.vikram.trendingrepos.data.repository.Repository
import com.vikram.trendingrepos.data.repository.RepositoryImpl
import com.vikram.trendingrepos.data.repository.remote.RemoteDataProvider
import com.vikram.trendingrepos.data.repository.remote.RemoteDataProviderImpl
import com.vikram.trendingrepos.data.services.ApiService
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun provideRemoteDataProvider(apiService: ApiService): RemoteDataProvider {
        return RemoteDataProviderImpl(apiService)
    }

    @Provides
    fun provideRepository(remoteDataProvider: RemoteDataProvider): Repository {
        return RepositoryImpl(remoteDataProvider)
    }

}