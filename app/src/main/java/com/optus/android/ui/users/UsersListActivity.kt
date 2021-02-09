package com.optus.android.ui.users

import android.os.Bundle
import androidx.activity.viewModels
import com.optus.android.R
import com.optus.android.base.ViewModelActivity
import com.optus.android.databinding.ListItemsBinding

class UsersListActivity : ViewModelActivity<UsersListViewModel>() {
    override val viewModel: UsersListViewModel by viewModels {
        viewModelFactory
    }

    private val binding: ListItemsBinding by binding(R.layout.list_items)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.apply {
            lifecycleOwner = this@UsersListActivity
            listViewModel = viewModel
        }
        viewModel.fetchUsers()
    }
}

