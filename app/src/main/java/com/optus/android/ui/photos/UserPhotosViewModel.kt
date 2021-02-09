package com.optus.android.ui.photos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.optus.android.R
import com.optus.android.network.data.Photo
import com.optus.android.ui.common.ErrorEmptyState
import com.optus.android.ui.common.ListFetchedState
import com.optus.android.ui.common.ListViewModel
import com.optus.android.ui.common.ProgressState
import kotlinx.coroutines.launch
import javax.inject.Inject

class UserPhotosViewModel @Inject constructor(private val userPhotosRepository: UserPhotosRepository) :
    ListViewModel() {

    private val _photoLists: MutableLiveData<List<Photo>> = MutableLiveData(emptyList())
    val photoLists: LiveData<List<Photo>> = _photoLists

    fun fetchUserPhotos(albumId: Long) {
        if (_viewState.value == ListFetchedState || _viewState.value == ProgressState)
            return
        _viewState.value = ProgressState
        viewModelScope.launch {
            try {
                val photos = userPhotosRepository.getUserPhotos(albumId)
                if (photos.isEmpty()) {
                    _viewState.value = ErrorEmptyState("Empty list", R.string.empty_list)
                } else {
                    _viewState.value = ListFetchedState
                    _photoLists.value = photos
                }
            } catch (exception: Exception) {
                _viewState.value =
                    ErrorEmptyState(
                        exception.message ?: "Error loading list",
                        R.string.error_fetching_list
                    )
            }
        }
    }
}