package com.petproject.moviesapp.data.mappers

import com.petproject.moviesapp.data.network.model.review.ReviewDto
import com.petproject.moviesapp.data.network.model.review.ReviewResponseDto
import com.petproject.moviesapp.domain.ReviewRating
import com.petproject.moviesapp.domain.entities.Review

class ReviewMapper {
    private fun dtoToEntitySingle(dto: ReviewDto) = Review(
        authorName = dto.authorName,
        reviewText = dto.reviewText,
        rating = convertRatingToEnum(dto.type)
    )

    private fun convertRatingToEnum(rating: String) = when (rating) {
        "Позитивный" -> ReviewRating.POSITIVE
        "Негативнй" -> ReviewRating.NEGATIVE
        else -> ReviewRating.NEUTRAL
    }

    fun dtoToEntity(reviewResponseDto: ReviewResponseDto): List<Review> {
        return reviewResponseDto.reviews.map { dtoToEntitySingle(it) }
    }
}