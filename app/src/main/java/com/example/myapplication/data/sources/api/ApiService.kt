package com.example.myapplication.data.sources.api

import com.example.myapplication.data.sources.api.model.response.FeedResponse
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @GET
    suspend fun getFeeds(
        @Url url: String
    ): Response<FeedResponse>

}