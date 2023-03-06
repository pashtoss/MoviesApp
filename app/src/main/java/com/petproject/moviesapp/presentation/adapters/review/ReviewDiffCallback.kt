package com.petproject.moviesapp.presentation.adapters.review

import androidx.recyclerview.widget.DiffUtil
import com.petproject.moviesapp.domain.entities.Review

class ReviewDiffCallback : DiffUtil.ItemCallback<Review>() {
    override fun areItemsTheSame(oldItem: Review, newItem: Review): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Review, newItem: Review): Boolean {
        return oldItem == newItem
    }
}