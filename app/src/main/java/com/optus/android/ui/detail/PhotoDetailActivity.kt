package com.optus.android.ui.detail

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.optus.android.R
import com.optus.android.base.BaseActivity
import com.optus.android.databinding.ActivityPhotoDetailBinding
import com.optus.android.network.data.Photo

class PhotoDetailActivity : BaseActivity() {

    companion object {
        private const val PHOTO = "Photo"
        fun launchIntent(context: Context, photo: Photo): Intent {
            return Intent(context, PhotoDetailActivity::class.java)
                .apply {
                    putExtra(PHOTO, photo)
                }
        }

        private fun getPhoto(intent: Intent): Photo? {
            return if (intent.hasExtra(PHOTO))
                intent.getParcelableExtra(PHOTO)
            else
                null
        }
    }

    val binding: ActivityPhotoDetailBinding by binding(R.layout.activity_photo_detail)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_detail)
        val photo = getPhoto(intent)
        if (photo == null) {
            Toast.makeText(this, getString(R.string.error_invalid_photo), Toast.LENGTH_SHORT)
                .show()
            finish()
            return
        }
        initViews(photo)
    }

    private fun initViews(photo: Photo) {
        supportActionBar?.apply {
            title = getString(R.string.title_album_id, photo.albumId)
            setDisplayHomeAsUpEnabled(true)
        }
        binding.photo = photo
    }
}