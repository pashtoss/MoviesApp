package com.petproject.moviesapp.presentation.viewmodels

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.petproject.moviesapp.data.repository.RepositoryImpl
import com.petproject.moviesapp.domain.MovieDetailsMode
import com.petproject.moviesapp.domain.entities.Movie
import com.petproject.moviesapp.domain.entities.Review
import com.petproject.moviesapp.domain.entities.Trailer
import com.petproject.moviesapp.domain.usecases.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieDetailsViewModel(
    application: Application,
    private val movie: Movie,
    mode: MovieDetailsMode
) : ViewModel() {
    private val repository = RepositoryImpl.getInstance(application)

    private val getReviewsUseCase = GetReviewsUseCase(repository)
    private val getTrailersUseCase = GetTrailersUseCase(repository)

    private val getSavedTrailersUseCase = GetSavedTrailersUseCase(repository)
    private val getSavedReviewsUseCase = GetSavedReviewsUseCase(repository)
    private val existsSavedMovieUseCase = ExistsSavedMovieUseCase(repository)
    private val saveMovieUseCase = SaveMovieUseCase(repository)
    private val removeMovieUseCase = RemoveMovieUseCase(repository)

    private val _trailers = MutableLiveData<List<Trailer>>()
    val trailers: LiveData<List<Trailer>> get() = _trailers

    private val _reviews = MutableLiveData<List<Review>>()
    val reviews: LiveData<List<Review>> get() = _reviews

    var isFavorite: LiveData<Boolean> = existsSavedMovieUseCase(movie.id)

    init {
        when (mode) {
            MovieDetailsMode.MODE_GENERAL -> {
                getReviewsOnline()
                getTrailersOnline()
            }
            MovieDetailsMode.MODE_FAVORITES -> {
                getReviewsOffline()
                getTrailersOffline()
            }
        }
    }

    private fun getTrailersOnline() {
        viewModelScope.launch {
            _trailers.value = withContext(Dispatchers.IO) { getTrailersUseCase(movie.id) }
        }
    }

    private fun getReviewsOnline() {
        viewModelScope.launch(Dispatchers.IO) {
            _reviews.postValue(getReviewsUseCase(movie.id))
        }
    }

    private fun getTrailersOffline() {
        viewModelScope.launch {
            _trailers.value = withContext(Dispatchers.IO) { getSavedTrailersUseCase(movie.id) }
        }
    }

    private fun getReviewsOffline() {
        viewModelScope.launch(Dispatchers.IO) {
            _reviews.postValue(getSavedReviewsUseCase(movie.id))
        }
    }

    fun onStarPressed() {
        viewModelScope.launch(Dispatchers.IO) {
            if (isFavorite.value != true) {
                val reviewList =
                    reviews.value ?: throw IllegalStateException("reviews value is null")
                val trailerList =
                    trailers.value ?: throw IllegalStateException("trailers value is null")
                saveMovieUseCase(movie, trailerList, reviewList)
            } else {
                removeMovieUseCase(movie.id)
            }
        }
    }
}