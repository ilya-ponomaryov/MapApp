package com.example.mapapp.common.usecases

import com.example.mapapp.common.usecases.models.Coordinate
import com.example.mapapp.common.coordinate.CoordinateRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface CoordinatesGetter {
    suspend operator fun invoke(): Flow<List<Coordinate>>
}

class CoordinatesGetterImpl @Inject constructor(
    private val repository: CoordinateRepository
) : CoordinatesGetter {
    override suspend fun invoke(): Flow<List<Coordinate>> = repository.getAllCoordinates()


}