package com.optus.android.ui.users

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.optus.android.R
import com.optus.android.network.data.User
import com.optus.android.ui.common.ErrorEmptyState
import com.optus.android.ui.common.ListFetchedState
import com.optus.android.ui.common.ListViewModel
import com.optus.android.ui.common.ProgressState
import kotlinx.coroutines.launch
import javax.inject.Inject

class UsersListViewModel @Inject constructor(private val usersListRepository: UsersListRepository) :
    ListViewModel() {


    private val _userLists: MutableLiveData<List<User>> = MutableLiveData(emptyList())
    val userLists: LiveData<List<User>> = _userLists

    fun fetchUsers() {
        if (_viewState.value == ListFetchedState || _viewState.value == ProgressState)
            return
        _viewState.value = ProgressState
        viewModelScope.launch {
            try {
                val users = usersListRepository.getUsers()
                if (users.isEmpty()) {
                    _viewState.value = ErrorEmptyState("Empty list", R.string.empty_list)
                } else {
                    _viewState.value = ListFetchedState
                    _userLists.value = users
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