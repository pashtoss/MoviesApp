package com.petproject.moviesapp.presentation.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.petproject.moviesapp.domain.MovieDetailsMode
import com.petproject.moviesapp.domain.entities.Movie

class MovieDetailsViewModelFactory(
    private val application: Application,
    private val movie: Movie,
    private val mode: MovieDetailsMode
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieDetailsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MovieDetailsViewModel(application, movie, mode) as T
        }
        throw RuntimeException("Unknown view model type")
    }
}