package com.optus.android.ui.detail

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.optus.android.R
import com.optus.android.network.data.Photo

class PhotoDetailActivity : AppCompatActivity() {

    companion object {
        private const val PHOTO = "Photo"
        fun launch(context: Context, photo: Photo): Intent {
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_detail)
        val photo = getPhoto(intent)
        photo?.let {
            print(it)
        }
    }
}