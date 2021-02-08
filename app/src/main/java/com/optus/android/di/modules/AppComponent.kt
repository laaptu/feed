package com.optus.android.di.modules

import com.optus.android.di.ApiModule
import com.optus.android.di.NetworkModule
import dagger.Component
import javax.inject.Singleton

@Component(modules = [ApiModule::class, NetworkModule::class])
@Singleton
interface AppComponent {

}