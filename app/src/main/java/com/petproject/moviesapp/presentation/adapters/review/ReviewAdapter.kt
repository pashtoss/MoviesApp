package com.petproject.moviesapp.presentation.adapters.review

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.ListAdapter
import com.petproject.moviesapp.R
import com.petproject.moviesapp.databinding.ReviewItemBinding
import com.petproject.moviesapp.domain.ReviewRating
import com.petproject.moviesapp.domain.entities.Review

class ReviewAdapter : ListAdapter<Review, ReviewViewHolder>(ReviewDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ReviewItemBinding.inflate(inflater, parent, false)
        return ReviewViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        val review = currentList[position]
        Log.d("Review", review.authorName + review.reviewText)
        holder.authorName.text = review.authorName
        holder.reviewText.text = review.reviewText
        setThumb(review.rating, holder.thumbImage)
    }

    private fun setThumb(rating: ReviewRating, imageView: ImageView) {
        val thumbImage = when (rating) {
            ReviewRating.POSITIVE -> R.drawable.image_thumb_up
            ReviewRating.NEGATIVE -> R.drawable.image_thumb_down
            ReviewRating.NEUTRAL -> R.drawable.image_thumb_neutral
        }
        imageView.setImageResource(thumbImage)
    }
}