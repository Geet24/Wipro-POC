package com.example.myapplication.presentation.feed

import android.content.Context
import androidx.annotation.IdRes
import androidx.fragment.app.FragmentManager
import com.example.myapplication.common.utils.FragmentTransactionHelper

class FeedListActivityNavigator constructor(
    @IdRes private val containerId: Int,
    private val fragmentManager: FragmentManager
) : FeedListFragmentNavigator    {

    fun addFeedListFragment() {
        FragmentTransactionHelper.replaceFragment(
            fragmentManager,
            FeedListFragment.newInstance(),
            containerId, addToBackStack = false
        )
    }

    override fun openDetailFromFeeList() {
      // TO implement feed on click
    }
}