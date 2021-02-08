package com.optus.android.network.data

data class User(val id: Long, val name: String, val email: String, val phone: String)
data class Photo(
    val id: Long,
    val albumId: Long,
    val title: String,
    val url: String,
    val thumbnailUrl: String
)