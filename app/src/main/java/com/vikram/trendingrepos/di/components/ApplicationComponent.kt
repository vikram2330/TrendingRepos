package com.vikram.trendingrepos.di.components

import com.vikram.trendingrepos.di.modules.ApplicationModule
import com.vikram.trendingrepos.di.modules.NetworkModule
import com.vikram.trendingrepos.di.modules.RepositoryModule
import com.vikram.trendingrepos.di.modules.ViewModelModule
import com.vikram.trendingrepos.ui.homescreen.HomeFragment
import dagger.Component


@Component(modules = [ApplicationModule::class, NetworkModule::class, RepositoryModule::class, ViewModelModule::class])
interface ApplicationComponent {
    fun inject(target: HomeFragment)
}