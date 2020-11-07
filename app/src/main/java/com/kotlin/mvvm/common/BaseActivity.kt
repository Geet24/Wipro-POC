package com.kotlin.mvvm.common

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import dagger.android.support.DaggerAppCompatActivity


abstract class BaseActivity<B : ViewDataBinding, out NV>(
    @LayoutRes
    private val layoutId: Int
) : DaggerAppCompatActivity() {

    lateinit var viewBinding: B

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = DataBindingUtil.setContentView(
            this, layoutId
        )
    }

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(newBase)
        val newOverride = Configuration(newBase?.resources?.configuration)
        if (newOverride.fontScale > 1.0f) {
            newOverride.fontScale = 1.0f
            applyOverrideConfiguration(newOverride)
        }
    }

    abstract fun getFragmentNavigator(): NV?
}
