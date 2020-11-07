package com.example.myapplication.presentation.feed

import android.os.Bundle
import com.example.myapplication.R
import com.example.myapplication.common.BaseActivity
import com.example.myapplication.databinding.ActivityMainBinding

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