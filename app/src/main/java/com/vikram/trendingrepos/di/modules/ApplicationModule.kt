package com.vikram.trendingrepos.di.modules

import android.content.Context
import com.accuweather.skyguard.injection.qualifiers.ApplicationContext
import com.vikram.trendingrepos.TrendingReposApp
import com.vikram.trendingrepos.utils.IRxSchedulers
import dagger.Module
import dagger.Provides
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Singleton


@Module
class ApplicationModule {

    @Provides
    @Singleton
    internal fun provideRxSchedulers(): IRxSchedulers {
        return object : IRxSchedulers {
            override fun main() = AndroidSchedulers.mainThread()
            override fun io() = Schedulers.io()
        }
    }

    @Provides
    @ApplicationContext
    fun provideAppContext(app: TrendingReposApp): Context = app.applicationContext

}