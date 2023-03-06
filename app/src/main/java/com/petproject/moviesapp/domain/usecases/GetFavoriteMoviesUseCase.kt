package com.petproject.moviesapp.domain.usecases

import com.petproject.moviesapp.domain.repository.Repository

class GetFavoriteMoviesUseCase(private val repository: Repository) {
    operator fun invoke() = repository.getFavoriteMovies()
}