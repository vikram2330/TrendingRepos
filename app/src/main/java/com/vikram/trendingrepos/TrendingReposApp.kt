package com.vikram.trendingrepos

import android.app.Application
import com.vikram.trendingrepos.di.components.DaggerApplicationComponent
import com.vikram.trendingrepos.di.modules.ApplicationModule


class TrendingReposApp : Application() {

    override fun onCreate() {
        super.onCreate()
        DaggerApplicationComponent.builder().applicationModule(ApplicationModule(this)).build()
    }
}