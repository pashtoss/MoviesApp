package com.petproject.moviesapp.presentation.adapters.trailer

import androidx.recyclerview.widget.RecyclerView
import com.petproject.moviesapp.databinding.TrailerItemBinding

class TrailerViewHolder(binding: TrailerItemBinding):RecyclerView.ViewHolder(binding.root) {
    val name = binding.textViewTrailerName
}