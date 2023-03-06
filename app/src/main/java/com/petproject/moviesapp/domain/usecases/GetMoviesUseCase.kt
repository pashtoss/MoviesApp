package com.petproject.moviesapp.domain.usecases

import com.petproject.moviesapp.domain.repository.Repository

class GetMoviesUseCase(private val repository: Repository) {
    suspend operator fun invoke() = repository.loadMovies()
}