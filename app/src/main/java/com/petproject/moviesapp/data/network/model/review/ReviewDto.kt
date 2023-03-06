package com.petproject.moviesapp.data.network.model.review

import com.google.gson.annotations.SerializedName

data class ReviewDto(
    @SerializedName("type")
    val type: String,
    @SerializedName("review")
    val reviewText: String,
    @SerializedName("author")
    val authorName: String
)
