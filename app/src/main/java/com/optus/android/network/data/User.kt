package com.optus.android.network.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class User(val id: Long, val name: String, val email: String, val phone: String)

@Parcelize
data class Photo(
    val id: Long,
    val albumId: Long,
    val title: String,
    val url: String,
    val thumbnailUrl: String
) : Parcelable