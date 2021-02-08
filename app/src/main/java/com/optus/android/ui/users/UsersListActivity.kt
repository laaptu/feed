package com.optus.android.ui.users

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.optus.android.R
import com.optus.android.base.ViewModelActivity
import com.optus.android.ui.photos.UserPhotosActivity

class UsersListActivity : ViewModelActivity<UsersListViewModel>() {
    override val viewModel: UsersListViewModel by viewModels {
        viewModelFactory
    }

    //private val binding: ActivityUsersListBinding by binding(R.layout.activity_users_list)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.fetchUsers()
        setContentView(R.layout.activity_users_list)
    }

    fun goToDetail(view: View){
        startActivity(Intent(this,UserPhotosActivity::class.java))
    }


}

