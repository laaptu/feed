package com.optus.android.di.component

import com.optus.android.MainApplication
import com.optus.android.di.modules.ActivityModules
import com.optus.android.di.modules.ApiModule
import com.optus.android.di.modules.NetworkModule
import com.optus.android.di.modules.viewmodel.ViewModels
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Component(
    modules = [AndroidInjectionModule::class, ActivityModules::class,
        ViewModels::class, ApiModule::class,
        NetworkModule::class]
)
@Singleton
interface AppComponent {
    fun inject(mainApplication: MainApplication)
}