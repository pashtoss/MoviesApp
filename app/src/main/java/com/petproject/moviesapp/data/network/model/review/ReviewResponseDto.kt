package com.petproject.moviesapp.data.network.model.review

import com.google.gson.annotations.SerializedName

data class ReviewResponseDto(
    @SerializedName("docs")
    val reviews: List<ReviewDto>
)
