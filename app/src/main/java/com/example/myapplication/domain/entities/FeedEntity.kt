package com.example.myapplication.domain.entities

import com.google.gson.annotations.SerializedName

data class FeedEntity(
    @SerializedName("title")
    val title: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("imageHref")
    val image: String?
)