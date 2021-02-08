package com.optus.android.ui.photos

import android.os.Bundle
import android.os.Handler
import androidx.activity.viewModels
import com.optus.android.R
import com.optus.android.base.ViewModelActivity
import com.optus.android.databinding.ActivityUserPhotosBinding
import com.optus.android.ui.detail.PhotoDetailActivity
import kotlin.random.Random

class UserPhotosActivity : ViewModelActivity<UserPhotosViewModel>() {
    override val viewModel: UserPhotosViewModel by viewModels {
        viewModelFactory
    }
    private val binding: ActivityUserPhotosBinding by binding(R.layout.activity_user_photos)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val id = Random.nextLong(1, 11)
        viewModel.fetchUserPhotos(id)
    }


}