package com.example.myapplication.presentation.feed

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.myapplication.R
import androidx.fragment.app.viewModels
import com.example.myapplication.common.BaseFragment
import com.example.myapplication.common.extension.gone
import com.example.myapplication.common.extension.visible
import com.example.myapplication.databinding.FragmentFeedListBinding
import com.example.myapplication.domain.AppResult
import com.example.myapplication.domain.entities.FeedEntity

class FeedListFragment :
    BaseFragment<FragmentFeedListBinding, FeedListFragmentNavigator>(R.layout.fragment_feed_list) {
    companion object {
        fun newInstance() = FeedListFragment()
    }

    private val feedListViewModel: FeedListViewModel by viewModels { viewModelFactory }
    override fun swipeRefreshLayout(): SwipeRefreshLayout? = viewBinding.swipeRefreshLayout
    private val feedListAdapter: FeedListAdapter by lazy {
        FeedListAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initFeedListView()
        setUpObservers()
    }

    override fun refreshView() {
        feedListViewModel.getFeeds()
    }

    private fun initFeedListView() {
        val linearLayoutManager = LinearLayoutManager(activity)
        viewBinding.rvNews.apply {
            layoutManager = linearLayoutManager
            adapter = feedListAdapter
        }
    }

    private fun setUpObservers() {
        feedListViewModel.feedsLiveData.observe(viewLifecycleOwner,
            Observer<AppResult<List<FeedEntity>>> { apiResult ->
                when (apiResult) {
                    is AppResult.Success -> {
                        apiResult.data?.let { feedList ->
                                feedListAdapter.refreshFeeds(feedList)
                                hideSwipeRefresh()
                                viewBinding.errorMsg.gone()
                                viewBinding.rvNews.visible()

                        }
                    }
                    is AppResult.Failure -> {
                        viewBinding.errorMsg.visible()
                        viewBinding.rvNews.gone()
                        hideSwipeRefresh()
                    }
                    is AppResult.Loading -> {
                        showSwipeRefresh()
                    }
                }
            })
    }

}