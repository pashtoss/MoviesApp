package com.petproject.moviesapp.presentation.adapters.movie

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.Glide
import com.petproject.moviesapp.R
import com.petproject.moviesapp.databinding.MovieItemBinding
import com.petproject.moviesapp.domain.entities.Movie

class MovieAdapter(
    private val onItemClickListener: (Movie) -> Unit,
    private val onScrollEndListener: () -> Unit = {}
) : ListAdapter<Movie, MovieViewHolder>(MovieDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = MovieItemBinding.inflate(inflater)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = getItem(position)
        holder.rating.text = movie.rating.toString()
        setRatingBackground(holder.rating, movie.rating)
        Glide.with(holder.itemView.context)
            .load(movie.posterUrl)
            .timeout(1500)
            .error(R.drawable.error_image_holder)
            .into(holder.imagePreview)
        holder.itemView.setOnClickListener { onItemClickListener(movie) }
        if (position == currentList.lastIndex - 6) {
            onScrollEndListener()
        }
    }

    private fun setRatingBackground(textView: TextView, rating: Double) {
        val resourceId = when (rating) {
            in 8.0..9.0 -> R.drawable.orange_circle_bg
            in 9.1..10.0 -> R.drawable.green_circle_bg
            else -> R.drawable.red_circle_bg
        }
        textView.setBackgroundResource(resourceId)
    }
}