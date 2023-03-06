package com.petproject.moviesapp.data.network.model.trailer

import com.google.gson.annotations.SerializedName

data class TrailerDto(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String,

)
