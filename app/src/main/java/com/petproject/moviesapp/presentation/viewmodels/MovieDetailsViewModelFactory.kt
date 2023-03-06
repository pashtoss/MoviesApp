package com.petproject.moviesapp.presentation.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.petproject.moviesapp.domain.entities.Movie

class MovieDetailsViewModelFactory(
    private val application: Application,
    private val movie: Movie
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieDetailsViewModel::class.java)) {
            return MovieDetailsViewModel(application, movie) as T
        }
        throw RuntimeException("Unknown view model type")
    }
}