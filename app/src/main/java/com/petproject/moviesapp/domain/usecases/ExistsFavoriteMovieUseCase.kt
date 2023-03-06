package com.petproject.moviesapp.domain.usecases

import com.petproject.moviesapp.domain.repository.Repository


class ExistsFavoriteMovieUseCase(private val repository: Repository) {
    operator fun invoke(movieId: Int) = repository.existsFavoriteMovie(movieId)
}