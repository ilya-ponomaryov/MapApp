package com.example.mapapp.coordinates.usecases

import com.example.mapapp.common.usecases.coordinates.CoordinateRepository
import com.example.mapapp.common.usecases.coordinates.models.Coordinate
import javax.inject.Inject

interface CoordinateSetter {
    suspend operator fun invoke(coordinate: Coordinate)
}

class CoordinateSetterImpl @Inject constructor(
    private val coordinateRepository: CoordinateRepository,
) : CoordinateSetter {
    override suspend fun invoke(coordinate: Coordinate) =
        coordinateRepository.saveCoordinate(coordinate)


}