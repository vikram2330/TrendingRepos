package com.vikram.trendingrepos

import android.content.Context
import com.vikram.trendingrepos.di.modules.ApplicationModule
import com.vikram.trendingrepos.di.modules.NetworkModule
import com.vikram.trendingrepos.di.modules.RepositoryModule
import dagger.Component
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, RepositoryModule::class, NetworkModule::class])
interface TestComponent {
    fun inject(repositoryTest: RepositoryTest)
}

class TestApplicationModule(private val app: TrendingReposApp) : ApplicationModule(app) {

    override fun provideAppContext(): Context = app.applicationContext
}

class TestNetworkModule : NetworkModule() {
    /**
     * will provide instance of okhttp client without using Application context
     *
     * @param context
     * @return
     */
    override fun provideOkHttpClient(context: Context): OkHttpClient {
        val httpBuilder = OkHttpClient.Builder()
        return httpBuilder.build()
    }
}