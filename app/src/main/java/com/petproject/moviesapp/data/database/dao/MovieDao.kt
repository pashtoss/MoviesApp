package com.petproject.moviesapp.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.petproject.moviesapp.data.database.model.MovieDbModel


@Dao
interface MovieDao {
    @Query("SELECT * FROM movies")
    fun getMovies(): LiveData<List<MovieDbModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMovie(movieDbModel: MovieDbModel)

    @Query("DELETE FROM movies WHERE id = :movieId")
    suspend fun removeMovie(movieId: Int)

    @Query("SELECT EXISTS(SELECT 1 FROM movies WHERE id = :movieId LIMIT 1)")
    fun existsMovie(movieId: Int): LiveData<Boolean>
}