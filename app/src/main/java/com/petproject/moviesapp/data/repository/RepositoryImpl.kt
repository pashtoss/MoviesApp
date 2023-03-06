package com.petproject.moviesapp.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.petproject.moviesapp.data.database.AppDatabase
import com.petproject.moviesapp.data.mappers.MovieMapper
import com.petproject.moviesapp.data.mappers.ReviewMapper
import com.petproject.moviesapp.data.mappers.TrailerMapper
import com.petproject.moviesapp.data.network.api.ApiFactory
import com.petproject.moviesapp.domain.entities.Movie
import com.petproject.moviesapp.domain.entities.Review
import com.petproject.moviesapp.domain.entities.Trailer
import com.petproject.moviesapp.domain.repository.Repository

//todo: make it as singleton realisation using context injection via dagger
class RepositoryImpl(application: Application) : Repository {

    private val apiService = ApiFactory.apiService
    private val movieDao = AppDatabase.getInstance(application).getMovieDao()
    private val movieMapper = MovieMapper()
    private val trailerMapper = TrailerMapper()
    private val reviewMapper = ReviewMapper()
    private var page = 1
    override suspend fun loadMovies(): List<Movie> {
        val response = apiService.getMovies(page)
        page += 1
        return movieMapper.dtoToEntityList(response)
    }

    override suspend fun loadTrailers(movieId: Int): List<Trailer> {
        val response = apiService.getTrailers(movieId)
        return trailerMapper.dtoToEntity(response)
    }

    override suspend fun loadReviews(movieId: Int): List<Review> {
        val response = apiService.getReviews(movieId)
        return reviewMapper.dtoToEntity(response)
    }

    override suspend fun addToFavorites(movie: Movie) {
        movieDao.addToFavorites(movieMapper.entityToDbModelSingle(movie))
    }

    override suspend fun removeFromFavorites(movieId: Int) {
        movieDao.removeFromFavorites(movieId)
    }

    override fun getFavoriteMovies(): LiveData<List<Movie>> {
        return MediatorLiveData<List<Movie>>().apply {
            addSource(movieDao.getFavoriteMovies()) {
                value = movieMapper.dbModelToEntityList(it)
            }
        }
    }

    override fun existsFavoriteMovie(movieId: Int): LiveData<Boolean> {
        return movieDao.existsFavoriteMovie(movieId)
    }
}