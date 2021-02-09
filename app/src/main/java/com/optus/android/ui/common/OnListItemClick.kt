package com.optus.android.ui.common

interface OnListItemClick<T> {
    fun itemClicked(item: T, index: Int)
}