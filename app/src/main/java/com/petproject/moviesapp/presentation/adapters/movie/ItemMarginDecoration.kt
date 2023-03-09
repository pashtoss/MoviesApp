package com.petproject.moviesapp.presentation.adapters.movie

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class ItemMarginDecoration(private val margin: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State,
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        with(outRect) {
            top = margin
            bottom = margin
            left = margin
            right = margin
        }
    }
}