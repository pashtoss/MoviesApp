package com.petproject.moviesapp.data.network.api

import com.petproject.moviesapp.data.network.model.movie.MovieDto
import com.petproject.moviesapp.data.network.model.movie.MovieResponseDto
import com.petproject.moviesapp.data.network.model.review.ReviewResponseDto
import com.petproject.moviesapp.data.network.model.trailer.TrailerResponseDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("review?page=1&limit=20")
    suspend fun getReviews(@Query("movieId") movieId: Int): ReviewResponseDto

    @GET("movie?limit=20&rating.imdb=7-10")
    suspend fun getMovies(
        @Query("page") page: Int,
        @Query("sortType") sortType: Int = SORT_DESCENDING,
        @Query("sortField") sortField: String = "votes.imdb"
    ): MovieResponseDto

    @GET("movie/{movieId}")
    suspend fun getTrailers(@Path("movieId") movieId: Int): TrailerResponseDto


    companion object {
        const val SORT_ASCENDING = 1
        const val SORT_DESCENDING = -1
    }
}