package com.optus.android.ui.common

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class ListViewModel : ViewModel() {
    protected val _viewState: MutableLiveData<ListViewState> = MutableLiveData(ProgressState)
    val viewState: LiveData<ListViewState> = _viewState
}