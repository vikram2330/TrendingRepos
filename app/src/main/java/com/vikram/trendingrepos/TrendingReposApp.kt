package com.vikram.trendingrepos

import android.app.Application
import com.vikram.trendingrepos.di.components.DaggerApplicationComponent


class TrendingReposApp : Application() {

    override fun onCreate() {
        super.onCreate()
        DaggerApplicationComponent.create()
    }
}