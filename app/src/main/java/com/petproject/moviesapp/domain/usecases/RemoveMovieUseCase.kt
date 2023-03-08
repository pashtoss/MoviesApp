package com.petproject.moviesapp.domain.usecases

import com.petproject.moviesapp.domain.repository.Repository

class RemoveMovieUseCase(private val repository: Repository) {
    suspend operator fun invoke(movieId: Int) = repository.removeMovie(movieId)
}