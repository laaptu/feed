package com.optus.android.ui.photos

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.optus.android.R
import com.optus.android.base.ViewModelActivity
import com.optus.android.databinding.ListItemsBinding
import com.optus.android.network.data.Photo
import com.optus.android.ui.common.OnListItemClick
import com.optus.android.ui.detail.PhotoDetailActivity

class UserPhotosActivity : ViewModelActivity<UserPhotosViewModel>(), OnListItemClick<Photo> {
    companion object {
        private const val ALBUM_ID = "albumId"
        private const val INVALID_ALBUM_ID = -1L
        fun launchIntent(context: Context, albumId: Long): Intent {
            return Intent(context, UserPhotosActivity::class.java).apply {
                putExtra(ALBUM_ID, albumId)
            }
        }

        fun getAlbumId(intent: Intent): Long =
            intent.getLongExtra(ALBUM_ID, INVALID_ALBUM_ID)

    }

    override val viewModel: UserPhotosViewModel by viewModels {
        viewModelFactory
    }
    private val binding: ListItemsBinding by binding(R.layout.list_items)
    private var albumId: Long = INVALID_ALBUM_ID

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        albumId = getAlbumId(intent)
        if (albumId == INVALID_ALBUM_ID) {
            Toast.makeText(this, getString(R.string.error_invalid_album_id), Toast.LENGTH_SHORT)
                .show()
            finish()
            return
        }
        initViews()
        initViewModel()
    }

    private fun initViews() {
        supportActionBar?.apply {
            title = getString(R.string.title_album_id, albumId)
            setDisplayHomeAsUpEnabled(true)
        }
        binding.apply {
            lifecycleOwner = this@UserPhotosActivity
            listViewModel = viewModel
        }
    }

    private fun initViewModel() {
        viewModel.photoLists.observe(this) {
            binding.recyclerView.layoutManager = LinearLayoutManager(this)
            binding.recyclerView.adapter = PhotosListAdapter(this, it)
        }
        viewModel.fetchUserPhotos(albumId)
    }

    override fun itemClicked(photo: Photo, index: Int) {
        startActivity(PhotoDetailActivity.launchIntent(this, photo))
    }

}