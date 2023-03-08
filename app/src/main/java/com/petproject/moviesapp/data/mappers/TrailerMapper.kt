package com.petproject.moviesapp.data.mappers

import com.petproject.moviesapp.data.database.model.TrailerDbModel
import com.petproject.moviesapp.data.network.model.trailer.TrailerDto
import com.petproject.moviesapp.data.network.model.trailer.TrailerResponseDto
import com.petproject.moviesapp.domain.entities.Trailer

class TrailerMapper {
    private fun dtoToEntitySingle(dto: TrailerDto) = Trailer(
        name = dto.name,
        url = dto.url
    )

    fun dtoToEntityList(trailerResponseDto: TrailerResponseDto): List<Trailer> {
        return trailerResponseDto.videos.trailers.map { dtoToEntitySingle(it) }
    }

    private fun entityToDbModelSingle(trailer: Trailer, movieId: Int) = TrailerDbModel(
        name = trailer.name,
        url = trailer.url,
        movieId = movieId
    )

    fun entityToDbModelList(list: List<Trailer>, movieId: Int): List<TrailerDbModel> {
        return list.map { entityToDbModelSingle(it, movieId) }
    }
}