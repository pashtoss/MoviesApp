package com.petproject.moviesapp.domain.usecases

import com.petproject.moviesapp.domain.repository.Repository

class GetSavedMoviesUseCase(private val repository: Repository) {
    operator fun invoke() = repository.getSavedMovies()
}