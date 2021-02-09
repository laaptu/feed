package com.optus.android.ui.common

sealed class ListViewState

object ProgressState : ListViewState()
class ErrorEmptyState(val msg: String, val stringResId: Int) : ListViewState()
object ListFetchedState : ListViewState()
object InitState : ListViewState()