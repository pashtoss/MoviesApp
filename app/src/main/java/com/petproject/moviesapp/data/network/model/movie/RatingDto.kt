package com.petproject.moviesapp.data.network.model.movie

import com.google.gson.annotations.SerializedName

data class RatingDto(
    @SerializedName("imdb") val imdb: Double
)