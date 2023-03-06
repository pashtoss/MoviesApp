package com.petproject.moviesapp.domain.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val id: Int,
    val year: Int,
    val name: String,
    val description: String,
    val posterUrl: String,
    val rating: Double
) : Parcelable
