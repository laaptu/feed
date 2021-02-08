package com.optus.android.network

object Network {
    const val apiBaseUrl = Urls.base
    const val connectionTimeOutInSec = 30L
    const val readTimeOutInSec = 30L
    const val writeTimeOutInSec = 30L
}

object Urls {
    const val base = "https://jsonplaceholder.typicode.com/"
    const val users = "users"
    const val photos = "photos"
}