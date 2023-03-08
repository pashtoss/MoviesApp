package com.petproject.moviesapp.domain.usecases

import com.petproject.moviesapp.domain.repository.Repository


class ExistsSavedMovieUseCase(private val repository: Repository) {
    operator fun invoke(movieId: Int) = repository.existsSavedMovie(movieId)
}