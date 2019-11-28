package com.vikram.trendingrepos

import android.app.Application
import com.google.android.gms.security.ProviderInstaller
import com.vikram.trendingrepos.di.components.ApplicationComponent
import com.vikram.trendingrepos.di.components.DaggerApplicationComponent
import com.vikram.trendingrepos.di.modules.ApplicationModule


class TrendingReposApp : Application() {

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        ProviderInstaller.installIfNeeded(this) //for TLS layer support on os version < 21
        applicationComponent =
            DaggerApplicationComponent.builder().applicationModule(ApplicationModule(this)).build()
    }

    companion object {
        private var INSTANCE: TrendingReposApp? = null
        @JvmStatic
        fun get(): TrendingReposApp = INSTANCE!!
    }
}