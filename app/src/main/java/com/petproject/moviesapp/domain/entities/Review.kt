package com.petproject.moviesapp.domain.entities

import com.petproject.moviesapp.domain.ReviewRating

data class Review(
    val authorName: String,
    val rating: ReviewRating,
    val reviewText: String
)
