package com.petproject.moviesapp.presentation.adapters.review

import androidx.recyclerview.widget.RecyclerView
import com.petproject.moviesapp.databinding.ReviewItemBinding

class ReviewViewHolder(binding: ReviewItemBinding):RecyclerView.ViewHolder(binding.root) {
    val authorName = binding.textViewAuthorName
    val reviewText = binding.textViewReviewText
    val thumbImage = binding.imageViewThumb
}