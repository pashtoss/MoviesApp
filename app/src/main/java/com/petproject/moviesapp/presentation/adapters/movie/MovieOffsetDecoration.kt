package com.petproject.moviesapp.presentation.adapters.movie

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class MovieOffsetDecoration(private val offset: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        val adapter = parent.adapter?: throw NullPointerException("RecyclerView adapter is null")
        val position = parent.getChildAdapterPosition(view)

        // Добавляем верхний отступ только для первой строки элементов
        if (position < 2) {
            outRect.top = offset
        }

        // Добавляем нижний отступ для всех элементов, кроме последнего
        if (position != adapter.itemCount.minus(1)) {
            outRect.bottom = offset
        }

        // Добавляем левый и правый отступы для элементов в правой колонке
        if (position % 2 == 1) {
            outRect.left = offset / 2
            outRect.right = offset
        } else {
            // Добавляем левый и правый отступы для элементов в левой колонке
            outRect.left = offset
            outRect.right = offset / 2
        }
    }
}
