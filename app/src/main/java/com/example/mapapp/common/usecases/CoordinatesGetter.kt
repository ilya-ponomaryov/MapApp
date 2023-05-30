package com.example.mapapp.common.usecases

import com.example.mapapp.common.usecases.models.Coordinate
import com.example.mapapp.common.coordinate.CoordinateRepository
import javax.inject.Inject

interface CoordinatesGetter {
    suspend operator fun invoke(): List<Coordinate>
}

class CoordinatesGetterImpl @Inject constructor(
    private val repository: CoordinateRepository
) : CoordinatesGetter {
    override suspend fun invoke(): List<Coordinate> = repository.getAllCoordinates()


}