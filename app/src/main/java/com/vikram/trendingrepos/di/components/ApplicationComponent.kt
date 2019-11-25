package com.vikram.trendingrepos.di.components

import com.vikram.trendingrepos.di.modules.ApplicationModule
import com.vikram.trendingrepos.di.modules.NetworkModule
import dagger.Component


@Component(modules = [ApplicationModule::class,NetworkModule::class])
interface ApplicationComponent {


}