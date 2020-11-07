package com.example.myapplication.data.sources.api

import com.example.myapplication.common.utils.ApiUrlBuilder
import com.example.myapplication.data.ErrorMapper
import com.example.myapplication.data.sources.api.model.response.FeedResponse
import com.example.myapplication.domain.AppError
import com.example.myapplication.domain.AppResult
import com.example.myapplication.domain.RetrofitResult
import com.example.myapplication.domain.entities.ErrorCodes
import com.example.myapplication.domain.entities.FeedEntity
import retrofit2.Response

interface FeedsApi {
    suspend fun getFeedsApi(): AppResult<FeedResponse>
}

class FeedsApiImpl(
    private val apiService: ApiService
) : FeedsApi {

    override suspend fun getFeedsApi(): AppResult<FeedResponse> {
        var response: Response<FeedResponse>? = null
        var exception: Exception? = null
        try {
            response = apiService.getFeeds(ApiUrlBuilder.getFeedsUrl())
        } catch (e: Exception) {
            e.printStackTrace()
            exception = e
        }
        return when (val result = ErrorMapper.checkAndMapError(response, exception)) {
            is RetrofitResult.Success -> {
                result.data?.let {
                    AppResult.Success(result.data)
                }?:kotlin.run {
                    AppResult.Failure(AppError(ErrorCodes.GENERIC_ERROR))
                }
            }
            is RetrofitResult.Failure -> AppResult.Failure(result.error)
        }
    }

    /*---------------------------------------Source to Domain Entity Mappers-------------------------------------------*/
    private fun feedResponseMapper(feedResponse: FeedResponse): List<FeedEntity> {
        return feedResponse.feeds.map { feed ->
            FeedEntity(
                title = feed.title,
                description = feed.description,
                image=feed.image
            )
        }
    }
}