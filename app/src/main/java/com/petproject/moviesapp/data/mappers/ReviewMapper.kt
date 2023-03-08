package com.petproject.moviesapp.data.mappers

import com.petproject.moviesapp.data.database.model.ReviewDbModel
import com.petproject.moviesapp.data.network.model.review.ReviewDto
import com.petproject.moviesapp.data.network.model.review.ReviewResponseDto
import com.petproject.moviesapp.domain.ReviewRating
import com.petproject.moviesapp.domain.entities.Review

class ReviewMapper {
    private fun dtoToEntitySingle(dto: ReviewDto) = Review(
        authorName = dto.authorName,
        reviewText = dto.reviewText,
        rating = convertStringToEnum(dto.type)
    )

    private fun convertStringToEnum(rating: String) = when (rating) {
        "Позитивный" -> ReviewRating.POSITIVE
        "Негативный" -> ReviewRating.NEGATIVE
        "Нейтральный" -> ReviewRating.NEUTRAL
        else -> throw IllegalArgumentException("Unknown rating value: $rating")
    }

    private fun convertEnumToString(reviewRating: ReviewRating) = when (reviewRating) {
        ReviewRating.POSITIVE -> "Позитивный"
        ReviewRating.NEGATIVE -> "Негативный"
        ReviewRating.NEUTRAL -> "Нейтральный"
    }

    fun dtoToEntity(reviewResponseDto: ReviewResponseDto): List<Review> {
        return reviewResponseDto.reviews.map { dtoToEntitySingle(it) }
    }

    private fun entityToDbModelSingle(review: Review, movieId: Int) = ReviewDbModel(
        authorName = review.authorName,
        reviewText = review.reviewText,
        rating = convertEnumToString(review.rating),
        movieId = movieId
    )

    private fun dbModelToEntitySingle(reviewDbModel: ReviewDbModel) = Review(
        authorName = reviewDbModel.authorName,
        reviewText = reviewDbModel.reviewText,
        rating = convertStringToEnum(reviewDbModel.rating),
    )

    fun dbModelToEntityList(list: List<ReviewDbModel>): List<Review> {
        return list.map { dbModelToEntitySingle(it) }
    }


    fun entityToDbModelList(list: List<Review>, movieId: Int): List<ReviewDbModel> {
        return list.map { entityToDbModelSingle(it, movieId) }
    }

}