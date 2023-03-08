package com.petproject.moviesapp.data.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE

@Entity(
    tableName = "trailers",
    primaryKeys = ["name", "url"],
    foreignKeys = [ForeignKey(
        MovieDbModel::class,
        parentColumns = ["id"],
        childColumns = ["movie_id"],
        onDelete = CASCADE,
    )]
)
data class TrailerDbModel(
    @ColumnInfo(name = "movie_id") val movieId: Int,
    val name: String,
    val url: String
)
