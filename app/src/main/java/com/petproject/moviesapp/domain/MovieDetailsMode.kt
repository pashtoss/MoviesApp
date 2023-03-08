package com.petproject.moviesapp.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
enum class MovieDetailsMode : Parcelable {
    MODE_GENERAL, MODE_FAVORITES
}