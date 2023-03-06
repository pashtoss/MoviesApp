package com.petproject.moviesapp.data.network.model.movie

import com.google.gson.annotations.SerializedName

data class MovieDto(
    @SerializedName("id")
    val id: Int,
    @SerializedName("year")
    val year: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("poster")
    val poster: PosterDto,
    @SerializedName("rating")
    val rating: RatingDto
)
