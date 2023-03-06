package com.petproject.moviesapp.presentation.adapters.trailer

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.petproject.moviesapp.databinding.TrailerItemBinding
import com.petproject.moviesapp.domain.entities.Trailer

class TrailerAdapter(
    private val onClickListener: (String) -> Unit
) : ListAdapter<Trailer, TrailerViewHolder>(TrailerDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrailerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return TrailerViewHolder(TrailerItemBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: TrailerViewHolder, position: Int) {
        val trailer = currentList[position]
        holder.name.text = trailer.name
        holder.itemView.setOnClickListener { onClickListener(trailer.url) }
    }

}