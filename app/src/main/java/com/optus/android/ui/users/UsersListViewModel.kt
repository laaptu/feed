package com.optus.android.ui.users

import androidx.lifecycle.viewModelScope
import com.optus.android.R
import com.optus.android.ui.common.ErrorEmptyState
import com.optus.android.ui.common.ListFetchedState
import com.optus.android.ui.common.ListViewModel
import com.optus.android.ui.common.ProgressState
import kotlinx.coroutines.launch
import javax.inject.Inject

class UsersListViewModel @Inject constructor(private val usersListRepository: UsersListRepository) :
    ListViewModel() {

    fun fetchUsers() {
        if (_viewState.value == ListFetchedState)
            return
        _viewState.value = ProgressState
        viewModelScope.launch {
            try {
                val users = usersListRepository.getUsers()
                if (users.isEmpty()) {
                    _viewState.value = ErrorEmptyState("Empty list", R.string.empty_list)
                } else {
                    _viewState.value = ListFetchedState
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