package com.optus.android.ui.users

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.optus.android.R
import com.optus.android.databinding.UserItemBinding
import com.optus.android.network.data.User
import com.optus.android.ui.common.OnListItemClick


class UsersListAdapter(private val onListItemClick: OnListItemClick<User>, val users: List<User>) :
    RecyclerView.Adapter<UserItemViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding =
            DataBindingUtil.inflate<UserItemBinding>(
                inflater, R.layout.user_item,
                parent,
                false
            )
        return UserItemViewHolder(binding)
    }

    override fun getItemCount(): Int = users.size

    override fun onBindViewHolder(holder: UserItemViewHolder, position: Int) {
        holder.binding.apply {
            user = users[position]
            userItemView.setOnClickListener {
                onListItemClick.itemClicked(users[position], position)
            }
            executePendingBindings()
        }
    }

}

class UserItemViewHolder(val binding: UserItemBinding) : RecyclerView.ViewHolder(binding.root)