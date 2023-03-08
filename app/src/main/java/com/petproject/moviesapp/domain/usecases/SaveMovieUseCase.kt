package com.petproject.moviesapp.domain.usecases

import com.petproject.moviesapp.domain.entities.Movie
import com.petproject.moviesapp.domain.entities.Review
import com.petproject.moviesapp.domain.entities.Trailer
import com.petproject.moviesapp.domain.repository.Repository

class SaveMovieUseCase(private val repository: Repository) {
    suspend operator fun invoke(
        movie: Movie,
        trailers: List<Trailer>,
        reviews: List<Review>
    ) = repository.saveMovie(movie, trailers, reviews)
}
