package com.petproject.moviesapp.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.petproject.moviesapp.data.database.model.ReviewDbModel
import com.petproject.moviesapp.domain.entities.Review

@Dao
interface ReviewDao {

    @Query("SELECT * FROM reviews WHERE movie_id = :movieId")
    suspend fun getReviews(movieId: Int): List<ReviewDbModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addReviews(reviews: List<ReviewDbModel>)
}