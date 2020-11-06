package com.example.myapplication.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.annotation.LayoutRes
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.myapplication.R
import com.example.myapplication.common.di.ViewModelFactory
import dagger.android.support.DaggerFragment
import timber.log.Timber
import javax.inject.Inject

abstract class BaseFragment<B : ViewDataBinding, NV : BaseNavigator>(
    @LayoutRes private val layoutId: Int
) : DaggerFragment() {
    //This is nullable as Fragments outlive their views.
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private var _viewBinding: B? = null


    /*This property is only valid between onCreateView and onDestroyView.
      This object returns _binding but thanks to !!(non-null asserted)
      we don't have to use ?(safe operator) everywhere in the code.*/
    val viewBinding get() = _viewBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.onBackPressedDispatcher?.addCallback(this, backPressCallback)
    }

    //Child fragments should not override this method and use onViewCreated instead.
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _viewBinding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        viewBinding.lifecycleOwner = viewLifecycleOwner
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleSwipeRefresh()
    }

    protected var navigator: NV? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val activity = activity
        if (activity is BaseActivity<*, *>) {
            try {
                @Suppress("UNCHECKED_CAST")
                navigator = activity.getFragmentNavigator() as NV
            } catch (e: Exception) {
                Timber.e("Activity Navigator should implement Fragment navigator")
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _viewBinding = null
    }

    protected open fun onBackPressHandled(): Boolean = false

    private val backPressCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            if (!onBackPressHandled()) {
                isEnabled = false
                activity?.onBackPressed()
            }
        }
    }

    private fun handleSwipeRefresh() {
        swipeRefreshLayout()?.setOnRefreshListener {
            refreshView()
        }
        swipeRefreshLayout()?.setColorSchemeColors(
            ContextCompat.getColor(requireContext(), R.color.orange),
            ContextCompat.getColor(requireContext(), R.color.darkRed),
            ContextCompat.getColor(requireContext(), R.color.brick),
        )
    }

    fun hideSwipeRefresh() {
        when (swipeRefreshLayout()?.isRefreshing) {
            true -> swipeRefreshLayout()?.isRefreshing = false
        }
    }

    fun showSwipeRefresh() {
        when (swipeRefreshLayout()?.isRefreshing) {
            false -> swipeRefreshLayout()?.isRefreshing = true
        }
    }

    open fun swipeRefreshLayout(): SwipeRefreshLayout? {
        return null
    }

    open fun refreshView() {}
}