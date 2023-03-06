package com.petproject.moviesapp.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class MovieDbModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val year: Int,
    val name: String,
    val description: String,
    val posterUrl: String,
    val rating: Double
)

