package com.vikram.trendingrepos.di.components

import com.vikram.trendingrepos.di.modules.ApplicationModule
import dagger.Component


@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {


}