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


//todo: add injection via dagger
class RepositoryImpl private constructor(application: Application) : Repository {

    private val apiService = ApiFactory.apiService
    private val movieDao = AppDatabase.getInstance(application).getMovieDao()
    private val reviewDao = AppDatabase.getInstance(application).getReviewDao()
    private val trailerDao = AppDatabase.getInstance(application).getTrailerDao()
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
        return trailerMapper.dtoToEntityList(response)
    }

    override suspend fun loadReviews(movieId: Int): List<Review> {
        val response = apiService.getReviews(movieId)
        return reviewMapper.dtoToEntity(response)
    }

    override suspend fun getSavedTrailers(movieId: Int): List<Trailer> {
        return trailerDao.getTrailers(movieId)
    }

    override suspend fun getSavedReviews(movieId: Int): List<Review> {
        return reviewMapper.dbModelToEntityList(reviewDao.getReviews(movieId))
    }

    override suspend fun saveMovie(movie: Movie, trailers: List<Trailer>, reviews: List<Review>) {
        movieDao.addMovie(movieMapper.entityToDbModelSingle(movie))
        trailerDao.addTrailers(trailerMapper.entityToDbModelList(trailers, movie.id))
        reviewDao.addReviews(reviewMapper.entityToDbModelList(reviews, movie.id))
    }

    override suspend fun removeMovie(movieId: Int) {
        movieDao.removeMovie(movieId)
    }

    override fun getSavedMovies(): LiveData<List<Movie>> {
        return MediatorLiveData<List<Movie>>().apply {
            addSource(movieDao.getMovies()) {
                value = movieMapper.dbModelToEntityList(it)
            }
        }
    }

    override fun existsSavedMovie(movieId: Int): LiveData<Boolean> {
        return movieDao.existsMovie(movieId)
    }

    companion object {
        private var instance: RepositoryImpl? = null
        private val LOCK = Unit
        fun getInstance(application: Application): RepositoryImpl {
            instance?.let {
                return it
            }
            synchronized(LOCK) {
                instance?.let {
                    return it
                }
                val newInstance = RepositoryImpl(application)
                instance = newInstance
                return newInstance
            }

        }
    }
}