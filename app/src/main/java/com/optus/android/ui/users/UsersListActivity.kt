package com.optus.android.ui.users

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.optus.android.R
import com.optus.android.base.ViewModelActivity
import com.optus.android.databinding.ListItemsBinding
import com.optus.android.network.data.User
import com.optus.android.ui.common.OnListItemClick
import com.optus.android.ui.photos.UserPhotosActivity

class UsersListActivity : ViewModelActivity<UsersListViewModel>(), OnListItemClick<User> {
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
        initViewModel()
    }

    private fun initViewModel() {
        viewModel.userLists.observe(this) {
            binding.recyclerView.layoutManager = LinearLayoutManager(this@UsersListActivity)
            binding.recyclerView.adapter = UsersListAdapter(this, it)
        }
        viewModel.fetchUsers()
    }

    override fun itemClicked(user: User, index: Int) {
        startActivity(UserPhotosActivity.launchIntent(this, user.id))
    }
}

