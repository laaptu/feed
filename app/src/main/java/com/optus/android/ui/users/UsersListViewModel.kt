package com.optus.android.ui.users

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class UsersListViewModel @Inject constructor(private val usersListRepository: UsersListRepository) :
    ViewModel() {
    fun fetchUsers() {
        viewModelScope.launch {
            val users = usersListRepository.getUsers()
            users.forEach {
                println(it)
            }
        }
    }
}