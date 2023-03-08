package com.petproject.moviesapp.data.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE

@Entity(
    tableName = "reviews",
    primaryKeys = ["author", "rating", "text"],
    foreignKeys = [ForeignKey(
        MovieDbModel::class,
        parentColumns = ["id"],
        childColumns = ["movie_id"],
        onDelete = CASCADE
    )]
)
data class ReviewDbModel(
    @ColumnInfo(name = "movie_id") val movieId: Int,
    @ColumnInfo(name = "author") val authorName: String,
    @ColumnInfo(name = "rating") val rating: String,
    @ColumnInfo(name = "text") val reviewText: String,

)