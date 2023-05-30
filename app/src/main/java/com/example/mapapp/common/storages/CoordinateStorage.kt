package com.example.mapapp.common.storages

import com.example.mapapp.common.storages.models.CoordinateEntity

object CoordinateStorage {
    private val coordinates = arrayListOf<CoordinateEntity>()

    fun insertCoordinate(coordinate: CoordinateEntity) {
        coordinates.add(coordinate)
    }

    fun getAllCoordinates(): List<CoordinateEntity> = coordinates.toList()
}