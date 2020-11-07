package com.kotlin.mvvm.data.sources.api

import com.kotlin.mvvm.data.sources.api.model.response.FeedResponse
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @GET
    suspend fun getFeeds(
        @Url url: String
    ): Response<FeedResponse>

}