package com.petproject.moviesapp.data.network.model.trailer

import com.google.gson.annotations.SerializedName

data class TrailerResponseDto(
    @SerializedName("videos")
    val videos: TrailersListDto
)
