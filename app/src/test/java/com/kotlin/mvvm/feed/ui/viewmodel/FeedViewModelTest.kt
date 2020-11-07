package com.kotlin.mvvm.feed.ui.viewmodel

import androidx.lifecycle.Observer
import com.kotlin.mvvm.testrules.base.BaseTestRule
import com.kotlin.mvvm.data.repository.FeedsRepository
import com.kotlin.mvvm.data.sources.api.model.response.FeedResponse
import com.kotlin.mvvm.domain.AppError
import com.kotlin.mvvm.domain.AppResult
import com.kotlin.mvvm.presentation.feed.FeedListViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations


class FeedViewModelTest :BaseTestRule(){
    @Mock
    lateinit var repositoryImpl: FeedsRepository
    private val error = AppResult.Failure(AppError("error while fetching "))
    @Mock
    lateinit var observerFeedResponse: Observer<AppResult<FeedResponse>>
    lateinit var viewModel: FeedListViewModel;


    @Before
    fun setUp() {
        // init to all Annotations so that we get all mock instance ready
        MockitoAnnotations.initMocks(this)
        viewModel = FeedListViewModel(repositoryImpl)
        viewModel.feedsLiveData.observeForever(observerFeedResponse)
        Dispatchers.setMain(coroutineTestRule.testDispatcher)
    }
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun testFetchFeedsSuccess() = runBlockingTest {
        val feedResponse = Mockito.mock(FeedResponse::class.java)
        val data = AppResult.Success(feedResponse)
        Mockito.`when`(
            repositoryImpl.getFeeds()
        ).thenReturn(data)

        viewModel.getFeeds()
        Mockito.verify(observerFeedResponse).onChanged(AppResult.Loading(null))
        Mockito.verify(observerFeedResponse).onChanged(data)
    }

    @Test
    fun testFetchFeedsError() = runBlockingTest {
        Mockito.`when`(
            repositoryImpl.getFeeds()
        ).thenReturn(error)

        viewModel.getFeeds()
        Mockito.verify(observerFeedResponse).onChanged(AppResult.Loading(null))
        Mockito.verify(observerFeedResponse).onChanged(error)
    }
}