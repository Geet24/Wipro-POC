package com.kotlin.mvvm.presentation.feed

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.kotlin.mvvm.R
import androidx.fragment.app.viewModels
import com.kotlin.mvvm.common.BaseFragment
import com.kotlin.mvvm.common.extension.gone
import com.kotlin.mvvm.common.extension.visible
import com.kotlin.mvvm.data.sources.api.model.response.FeedResponse
import com.kotlin.mvvm.databinding.FragmentFeedListBinding
import com.kotlin.mvvm.domain.AppResult

class FeedListFragment :
    BaseFragment<FragmentFeedListBinding, FeedListFragmentNavigator>(R.layout.fragment_feed_list) {

    private val feedListViewModel: FeedListViewModel by viewModels { viewModelFactory }
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

    override fun swipeRefreshLayout(): SwipeRefreshLayout? = viewBinding.swipeRefreshLayout

    private fun initFeedListView() {
        val linearLayoutManager = LinearLayoutManager(activity)
        viewBinding.rvNews.apply {
            layoutManager = linearLayoutManager
            adapter = feedListAdapter
        }
    }

    private fun setUpObservers() {
        feedListViewModel.feedsLiveData.observe(viewLifecycleOwner,
            Observer<AppResult<FeedResponse>> { apiResult ->
                when (apiResult) {
                    is AppResult.Success -> {
                        apiResult.data?.let { feedResponse ->
                            handleSuccessFeedResponse(feedResponse)
                        }
                    }
                    is AppResult.Failure -> {
                        handleFailureFeedResponse()
                    }
                    is AppResult.Loading -> {
                        showSwipeRefresh()
                    }
                }
            })
    }

    private fun handleFailureFeedResponse() {
        viewBinding.errorMsg.visible()
        viewBinding.rvNews.gone()
        hideSwipeRefresh()
    }

    private fun handleSuccessFeedResponse(feedResponse: FeedResponse) {
        viewBinding.title.text = feedResponse.title
        feedListAdapter.refreshFeeds(feedResponse.feeds)
        hideSwipeRefresh()
        viewBinding.errorMsg.gone()
        viewBinding.rvNews.visible()
    }

    companion object {
        fun newInstance() = FeedListFragment()
    }

}