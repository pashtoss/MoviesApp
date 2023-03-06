package com.petproject.moviesapp.domain.usecases

import com.petproject.moviesapp.domain.entities.Movie
import com.petproject.moviesapp.domain.repository.Repository

class AddToFavoritesUseCase(private val repository: Repository) {
    suspend operator fun invoke(movie: Movie) = repository.addToFavorites(movie)
}
