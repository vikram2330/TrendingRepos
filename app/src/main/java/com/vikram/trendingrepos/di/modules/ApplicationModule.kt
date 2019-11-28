package com.vikram.trendingrepos.di.modules

import android.content.Context
import com.vikram.trendingrepos.TrendingReposApp
import com.vikram.trendingrepos.di.qualifiers.ApplicationContext
import com.vikram.trendingrepos.utils.IRxSchedulers
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Singleton


@Module
open class ApplicationModule(private val app: TrendingReposApp) {

    @Provides
    @Singleton
    internal fun provideRxSchedulers(): IRxSchedulers {
        return object : IRxSchedulers {
            override fun compute(): Scheduler = Schedulers.computation()
            override fun main() = AndroidSchedulers.mainThread()
            override fun io() = Schedulers.io()
        }
    }

    @Provides
    @ApplicationContext
    open fun provideAppContext(): Context = app.applicationContext

}