package com.kotlin.mvvm.data.repository


import com.kotlin.mvvm.data.sources.api.FeedsApi
import com.kotlin.mvvm.data.sources.api.model.response.FeedResponse
import com.kotlin.mvvm.domain.AppResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface FeedsRepository {
    suspend fun getFeeds(): AppResult<FeedResponse>
}

class FeedsRepositoryImpl(
    private val feedsApi: FeedsApi) : FeedsRepository {
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
    override suspend fun getFeeds(): AppResult<FeedResponse> {
        return withContext(dispatcher) {
            feedsApi.getFeedsApi()
        }
    }
}