package com.petproject.moviesapp.domain.repository

import androidx.lifecycle.LiveData
import com.petproject.moviesapp.domain.entities.Movie
import com.petproject.moviesapp.domain.entities.Review
import com.petproject.moviesapp.domain.entities.Trailer

interface Repository {
    suspend fun loadMovies(): List<Movie>

    suspend fun loadTrailers(movieId: Int): List<Trailer>

    suspend fun loadReviews(movieId: Int): List<Review>

    suspend fun addToFavorites(movie: Movie)

    suspend fun removeFromFavorites(movieId: Int)

    fun getFavoriteMovies(): LiveData<List<Movie>>

    fun existsFavoriteMovie(movieId: Int): LiveData<Boolean>
}