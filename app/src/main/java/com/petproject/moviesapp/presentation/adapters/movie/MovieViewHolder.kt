package com.petproject.moviesapp.presentation.adapters.movie

import androidx.recyclerview.widget.RecyclerView
import com.petproject.moviesapp.databinding.MovieItemBinding

class MovieViewHolder(binding: MovieItemBinding) : RecyclerView.ViewHolder(binding.root) {
    val imagePreview = binding.imageViewMoviePreview
    val rating = binding.textViewRating
}