package com.optus.android.ui.photos

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.optus.android.R
import com.optus.android.databinding.PhotoItemBinding
import com.optus.android.network.data.Photo
import com.optus.android.ui.common.OnListItemClick

class PhotosListAdapter(
    private val onListItemClick: OnListItemClick<Photo>,
    val photos: List<Photo>
) : RecyclerView.Adapter<PhotoItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<PhotoItemBinding>(
            inflater, R.layout.photo_item,
            parent,
            false
        )
        return PhotoItemViewHolder(binding)
    }

    override fun getItemCount(): Int = photos.size

    override fun onBindViewHolder(holder: PhotoItemViewHolder, position: Int) {
        holder.binding.apply {
            photo = photos[position]
            photoItemView.setOnClickListener {
                onListItemClick.itemClicked(photos[position], position)
            }
        }
    }

}

class PhotoItemViewHolder(val binding: PhotoItemBinding) : RecyclerView.ViewHolder(binding.root)