package com.optus.android.base

import androidx.lifecycle.ViewModel
import com.optus.android.di.modules.viewmodel.ViewModelProviderFactory
import javax.inject.Inject

abstract class ViewModelActivity<T : ViewModel> : BaseActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelProviderFactory
    abstract val viewModel: T
}