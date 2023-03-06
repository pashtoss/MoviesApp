package com.petproject.moviesapp.presentation.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.petproject.moviesapp.data.repository.RepositoryImpl
import com.petproject.moviesapp.domain.entities.Movie
import com.petproject.moviesapp.domain.usecases.GetMoviesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieListViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = RepositoryImpl(application)
    private val getMoviesUseCase = GetMoviesUseCase(repository)

    private var movieList = mutableListOf<Movie>()

    private val _currentMovieList = MutableLiveData<List<Movie>>()
    val currentMovieList: LiveData<List<Movie>> get() = _currentMovieList

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    init {
        loadMovies()
    }

    fun loadMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.postValue(true)
            movieList.addAll(getMoviesUseCase())
            withContext(Dispatchers.Main) {
                _currentMovieList.value = movieList
            }
            _isLoading.postValue(false)
        }
    }
}