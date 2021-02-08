package com.optus.android.ui.users

import com.optus.android.network.UserApiService
import com.optus.android.network.data.User
import javax.inject.Inject

class UsersListRepository @Inject constructor(private val userApiService: UserApiService) {
    suspend fun getUsers(): List<User> = userApiService.getUsers()
}