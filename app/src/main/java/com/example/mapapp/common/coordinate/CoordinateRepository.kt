package com.example.mapapp.common.coordinate

import com.example.mapapp.common.storages.CoordinateDao
import com.example.mapapp.common.usecases.models.Coordinate
import com.example.mapapp.common.storages.models.CoordinateEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface CoordinateRepository {
    suspend fun saveCoordinate(coordinate: Coordinate)

    suspend fun getAllCoordinates(): Flow<List<Coordinate>>
}

class CoordinateRepositoryImpl @Inject constructor(
    private val coordinateDao: CoordinateDao
) : CoordinateRepository {
    override suspend fun saveCoordinate(coordinate: Coordinate) =
        withContext(Dispatchers.IO) {
            coordinateDao.insertCoordinate(coordinate.toCoordinateEntity())
        }

    override suspend fun getAllCoordinates(): Flow<List<Coordinate>> {
        return coordinateDao.getAllCoordinates().map { list ->
            list.map { coordinateEntity -> coordinateEntity.toCoordinate() }
        }
    }
}

fun Coordinate.toCoordinateEntity() = CoordinateEntity(latitude = latitude, longitude = longitude)

fun CoordinateEntity.toCoordinate() = Coordinate(latitude, longitude)