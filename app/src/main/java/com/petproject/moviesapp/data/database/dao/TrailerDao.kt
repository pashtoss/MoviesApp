package com.petproject.moviesapp.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.petproject.moviesapp.data.database.model.TrailerDbModel
import com.petproject.moviesapp.domain.entities.Trailer

@Dao
interface TrailerDao {

    @Query("SELECT name, url FROM trailers WHERE movie_id = :movieId")
    suspend fun getTrailers(movieId: Int): List<Trailer>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addTrailers(trailers: List<TrailerDbModel>)
}