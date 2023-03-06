package com.petproject.moviesapp.data.network.model.trailer

import com.google.gson.annotations.SerializedName

data class TrailersListDto(
    @SerializedName("trailers")
    val trailers: List<TrailerDto>
)
