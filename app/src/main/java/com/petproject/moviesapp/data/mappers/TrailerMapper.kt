package com.petproject.moviesapp.data.mappers

import com.petproject.moviesapp.data.network.model.trailer.TrailerDto
import com.petproject.moviesapp.data.network.model.trailer.TrailerResponseDto
import com.petproject.moviesapp.domain.entities.Trailer

class TrailerMapper {
    private fun dtoToEntitySingle(dto: TrailerDto) = Trailer(
        name = dto.name,
        url = dto.url
    )

    fun dtoToEntity(trailerResponseDto: TrailerResponseDto): List<Trailer> {
        return trailerResponseDto.videos.trailers.map { dtoToEntitySingle(it) }
    }
}