package com.optus.android.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import dagger.android.AndroidInjection

abstract class BaseActivity : AppCompatActivity() {

    protected fun <T : ViewDataBinding> binding(@LayoutRes layoutId: Int): Lazy<T> =
        lazy {
            DataBindingUtil.setContentView<T>(this, layoutId)
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
    }
}