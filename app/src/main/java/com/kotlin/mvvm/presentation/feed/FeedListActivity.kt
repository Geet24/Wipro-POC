package com.kotlin.mvvm.presentation.feed

import android.os.Bundle
import com.kotlin.mvvm.R
import com.kotlin.mvvm.common.BaseActivity
import com.kotlin.mvvm.databinding.ActivityMainBinding

class FeedListActivity :BaseActivity<ActivityMainBinding, FeedListActivityNavigator>(R.layout.activity_main) {
    private var feedListActivityNavigator: FeedListActivityNavigator? = null

    override fun getFragmentNavigator(): FeedListActivityNavigator? {
       return feedListActivityNavigator
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        feedListActivityNavigator = FeedListActivityNavigator(R.id.fragmentContainer, supportFragmentManager)
        feedListActivityNavigator?.addFeedListFragment()
    }

    override fun onDestroy() {
        super.onDestroy()
        feedListActivityNavigator=null
    }

}