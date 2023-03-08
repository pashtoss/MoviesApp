package com.petproject.moviesapp.domain.usecases

import com.petproject.moviesapp.domain.repository.Repository

class GetSavedReviewsUseCase(private val repository: Repository) {
    suspend operator fun invoke(movieId: Int) = repository.getSavedReviews(movieId)
}