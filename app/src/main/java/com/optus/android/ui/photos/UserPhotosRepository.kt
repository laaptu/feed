package com.optus.android.ui.photos

import com.optus.android.network.PhotoApiService
import com.optus.android.network.data.Photo
import javax.inject.Inject

class UserPhotosRepository @Inject constructor(private val userPhotoApiService: PhotoApiService) {
    suspend fun getUserPhotos(userId: Long): List<Photo> =
        userPhotoApiService.getPhotos().filter {
            it.albumId == userId
        }
}