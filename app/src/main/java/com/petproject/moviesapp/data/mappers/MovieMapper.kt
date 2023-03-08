package com.petproject.moviesapp.data.mappers

import com.petproject.moviesapp.data.database.model.MovieDbModel
import com.petproject.moviesapp.data.network.model.movie.MovieDto
import com.petproject.moviesapp.data.network.model.movie.MovieResponseDto
import com.petproject.moviesapp.domain.entities.Movie

class MovieMapper {
    private fun dtoToEntitySingle(dto: MovieDto) = Movie(
        id = dto.id,
        year = dto.year,
        rating = dto.rating.imdb,
        posterUrl = dto.poster.imageUrl,
        name = dto.name,
        description = dto.description,
    )

    fun dtoToEntityList(movieResponseDto: MovieResponseDto): List<Movie> {
        return movieResponseDto.movies.map { dtoToEntitySingle(it) }
    }

    private fun dbModelToEntitySingle(movieDbModel: MovieDbModel) = Movie(
        id = movieDbModel.id,
        name = movieDbModel.name,
        year = movieDbModel.year,
        description = movieDbModel.description,
        rating = movieDbModel.rating,
        posterUrl = movieDbModel.posterUrl
    )

    fun entityToDbModelSingle(movie: Movie) = MovieDbModel(
        id = movie.id,
        name = movie.name,
        year = movie.year,
        description = movie.description,
        rating = movie.rating,
        posterUrl = movie.posterUrl
    )

    fun dbModelToEntityList(list: List<MovieDbModel>): List<Movie> {
        return list.map { dbModelToEntitySingle(it) }
    }

}