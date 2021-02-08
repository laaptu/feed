package com.optus.android.ui.photos

import androidx.lifecycle.ViewModel
import javax.inject.Inject
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class UserPhotosViewModel @Inject constructor(private val userPhotosRepository: UserPhotosRepository) :
    ViewModel() {

    fun fetchUserPhotos(userId: Long) {
        viewModelScope.launch {
            userPhotosRepository.getUserPhotos(userId).forEach {
                println(it)
            }
        }
    }
}