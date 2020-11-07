package com.kotlin.mvvm.data.sources.api.model.response

import com.kotlin.mvvm.domain.entities.FeedEntity
import com.google.gson.annotations.SerializedName

 data class FeedResponse(
    @SerializedName("title")
    val title: String?,
    @SerializedName("rows")
    var feeds: List<FeedEntity>
)
