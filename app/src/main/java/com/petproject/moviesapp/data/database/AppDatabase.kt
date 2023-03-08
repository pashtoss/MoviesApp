package com.petproject.moviesapp.data.database

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.petproject.moviesapp.data.database.dao.MovieDao
import com.petproject.moviesapp.data.database.dao.ReviewDao
import com.petproject.moviesapp.data.database.dao.TrailerDao
import com.petproject.moviesapp.data.database.model.MovieDbModel
import com.petproject.moviesapp.data.database.model.ReviewDbModel
import com.petproject.moviesapp.data.database.model.TrailerDbModel

@Database(
    entities = [MovieDbModel::class, ReviewDbModel::class, TrailerDbModel::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getMovieDao(): MovieDao
    abstract fun getTrailerDao(): TrailerDao
    abstract fun getReviewDao(): ReviewDao

    companion object {
        private const val DB_NAME = "favorite_movies.db"
        private var instance: AppDatabase? = null
        private val LOCK = Unit
        fun getInstance(application: Application): AppDatabase {
            instance?.let {
                return it
            }
            synchronized(LOCK) {
                instance?.let {
                    return it
                }
                val newInstance = Room.databaseBuilder(
                    application,
                    AppDatabase::class.java,
                    DB_NAME
                )
                    .fallbackToDestructiveMigration()
                    .build()
                instance = newInstance
                return newInstance
            }
        }
    }
}