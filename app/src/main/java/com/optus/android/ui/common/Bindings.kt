package com.optus.android.ui.common

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.progressindicator.CircularProgressIndicator
import com.google.android.material.textview.MaterialTextView

object Bindings {
    @JvmStatic
    @BindingAdapter("show")
    fun bindListViewState(view: View, listViewState: ListViewState) {
        var visibility = View.GONE
        when (view) {
            is CircularProgressIndicator -> if (listViewState == ProgressState) visibility =
                View.VISIBLE
            is MaterialTextView -> if (listViewState is ErrorEmptyState) {
                visibility = View.VISIBLE
                view.text = view.context.getString(listViewState.stringResId)
            }
            is RecyclerView -> if (listViewState == ListFetchedState) visibility = View.VISIBLE
        }
        view.visibility = visibility
    }
}