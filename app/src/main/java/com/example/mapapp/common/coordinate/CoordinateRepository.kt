package com.example.mapapp.common.coordinate

import com.example.mapapp.common.usecases.models.Coordinate
import com.example.mapapp.common.storages.CoordinateStorage
import com.example.mapapp.common.storages.models.CoordinateEntity

interface CoordinateRepository {
    suspend fun saveCoordinate(coordinate: Coordinate)

    suspend fun getAllCoordinates(): List<Coordinate>
}

class CoordinateRepositoryImpl : CoordinateRepository {
    override suspend fun saveCoordinate(coordinate: Coordinate) {
        CoordinateStorage.insertCoordinate(coordinate.toCoordinateEntity())
    }

    override suspend fun getAllCoordinates(): List<Coordinate> {
        return CoordinateStorage.getAllCoordinates()
            .map { coordinateEntity -> coordinateEntity.toCoordinate() }
    }
}

fun Coordinate.toCoordinateEntity() = CoordinateEntity(latitude, longitude)

fun CoordinateEntity.toCoordinate() = Coordinate(latitude, longitude)