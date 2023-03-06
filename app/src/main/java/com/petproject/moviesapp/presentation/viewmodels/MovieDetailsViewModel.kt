package com.petproject.moviesapp.presentation.viewmodels

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.petproject.moviesapp.data.repository.RepositoryImpl
import com.petproject.moviesapp.domain.entities.Movie
import com.petproject.moviesapp.domain.entities.Review
import com.petproject.moviesapp.domain.entities.Trailer
import com.petproject.moviesapp.domain.usecases.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieDetailsViewModel(application: Application, private val movie: Movie) : ViewModel() {
    private val repository = RepositoryImpl(application)
    private val getReviewsUseCase = GetReviewsUseCase(repository)
    private val getTrailersUseCase = GetTrailersUseCase(repository)
    private val existsFavoriteMovieUseCase = ExistsFavoriteMovieUseCase(repository)
    private val addToFavoritesUseCase = AddToFavoritesUseCase(repository)
    private val removeFromFavoritesUseCase = RemoveFromFavoritesUseCase(repository)

    private val _trailers = MutableLiveData<List<Trailer>>()
    val trailers: LiveData<List<Trailer>> get() = _trailers

    private val _reviews = MutableLiveData<List<Review>>()
    val reviews: LiveData<List<Review>> get() = _reviews

    var isFavorite: LiveData<Boolean> = existsFavoriteMovieUseCase(movie.id)

    init {
        getReviews()
        getTrailers()
    }


    private fun getTrailers() {
        viewModelScope.launch {
            _trailers.value = withContext(Dispatchers.IO) { getTrailersUseCase(movie.id) }
        }
    }

    private fun getReviews() {
        viewModelScope.launch(Dispatchers.IO) {
            _reviews.postValue(getReviewsUseCase(movie.id))
        }
    }

    fun onStarPressed() {
        viewModelScope.launch(Dispatchers.IO) {
            if (isFavorite.value != true) {
                addToFavoritesUseCase(movie)
            } else {
                removeFromFavoritesUseCase(movie.id)
            }
        }
    }
}