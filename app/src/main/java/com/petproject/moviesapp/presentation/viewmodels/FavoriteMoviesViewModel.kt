package com.petproject.moviesapp.presentation.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.petproject.moviesapp.data.repository.RepositoryImpl
import com.petproject.moviesapp.domain.entities.Movie
import com.petproject.moviesapp.domain.usecases.GetSavedMoviesUseCase

class FavoriteMoviesViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = RepositoryImpl.getInstance(application)
    private val getSavedMoviesUseCase = GetSavedMoviesUseCase(repository)
    val favoriteMovies: LiveData<List<Movie>> = getSavedMoviesUseCase()
}