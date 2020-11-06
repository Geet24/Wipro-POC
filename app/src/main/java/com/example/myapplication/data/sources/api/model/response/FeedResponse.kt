package com.example.myapplication.data.sources.api.model.response

import com.example.myapplication.domain.entities.FeedEntity
import com.google.gson.annotations.SerializedName

data class FeedResponse(
    @SerializedName("rows")
    var feeds: List<FeedEntity>
)
