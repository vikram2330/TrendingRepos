package com.vikram.trendingrepos.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vikram.trendingrepos.di.scope.ViewModelScope
import com.vikram.trendingrepos.ui.homescreen.HomeViewModel
import com.vikram.trendingrepos.utils.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelScope(HomeViewModel::class)
    abstract fun bindHomeViewModel(homeViewModel: HomeViewModel): ViewModel

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}