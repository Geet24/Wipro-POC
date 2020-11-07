package com.example.myapplication.data.repository


import com.example.myapplication.data.sources.api.FeedsApi
import com.example.myapplication.data.sources.api.model.response.FeedResponse
import com.example.myapplication.domain.AppResult
import com.example.myapplication.domain.entities.FeedEntity
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