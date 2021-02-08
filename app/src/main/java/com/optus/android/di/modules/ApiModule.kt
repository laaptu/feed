package com.optus.android.di.modules

import com.optus.android.network.PhotoApiService
import com.optus.android.network.UserApiService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class ApiModule {
    @Provides
    @Singleton
    fun getUserApiService(retrofit: Retrofit): UserApiService =
        retrofit.create(UserApiService::class.java)

    @Provides
    @Singleton
    fun getPhotoApiService(retrofit: Retrofit): PhotoApiService =
        retrofit.create(PhotoApiService::class.java)
}