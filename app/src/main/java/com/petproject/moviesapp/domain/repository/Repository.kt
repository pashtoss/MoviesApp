package com.petproject.moviesapp.domain.repository

import androidx.lifecycle.LiveData
import com.petproject.moviesapp.domain.entities.Movie
import com.petproject.moviesapp.domain.entities.Review
import com.petproject.moviesapp.domain.entities.Trailer

interface Repository {
    suspend fun loadMovies(): List<Movie>

    suspend fun loadTrailers(movieId: Int): List<Trailer>

    suspend fun loadReviews(movieId: Int): List<Review>

    suspend fun getSavedTrailers(movieId: Int): List<Trailer>

    suspend fun getSavedReviews(movieId: Int): List<Review>

    suspend fun saveMovie(movie: Movie, trailers: List<Trailer>, reviews: List<Review>)

    suspend fun removeMovie(movieId: Int)

    fun getSavedMovies(): LiveData<List<Movie>>

    fun existsSavedMovie(movieId: Int): LiveData<Boolean>
}