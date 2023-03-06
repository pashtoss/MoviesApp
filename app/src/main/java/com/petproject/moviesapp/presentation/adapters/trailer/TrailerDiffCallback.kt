package com.petproject.moviesapp.presentation.adapters.trailer

import androidx.recyclerview.widget.DiffUtil
import com.petproject.moviesapp.domain.entities.Trailer

class TrailerDiffCallback : DiffUtil.ItemCallback<Trailer>() {
    override fun areItemsTheSame(oldItem: Trailer, newItem: Trailer): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: Trailer, newItem: Trailer): Boolean {
        return oldItem == newItem
    }
}