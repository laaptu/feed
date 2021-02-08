package com.optus.android.network

import com.optus.android.network.data.Photo
import com.optus.android.network.data.User
import retrofit2.http.GET


interface UserApiService {
    @GET(Urls.users)
    suspend fun getUsers(): List<User>
}

interface PhotoApiService {
    @GET(Urls.photos)
    suspend fun getPhotos(): List<Photo>
}