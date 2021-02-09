package com.optus.android.ui.photos

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.optus.android.R
import com.optus.android.base.ViewModelActivity
import com.optus.android.databinding.ListItemsBinding

class UserPhotosActivity : ViewModelActivity<UserPhotosViewModel>() {
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val albumId: Long = getAlbumId(intent)
        if (albumId == INVALID_ALBUM_ID) {
            Toast.makeText(this, getString(R.string.error_invalid_album_id), Toast.LENGTH_SHORT)
                .show()
            finish()
            return
        }
        binding.apply {
            lifecycleOwner = this@UserPhotosActivity
            listViewModel = viewModel
        }
        viewModel.fetchUserPhotos(albumId)
    }


}