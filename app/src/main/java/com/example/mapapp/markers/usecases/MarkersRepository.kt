package com.example.mapapp.markers.usecases

import com.example.mapapp.common.storages.CoordinateDao
import com.example.mapapp.common.storages.models.CoordinateEntity
import com.example.mapapp.markers.models.Marker
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface MarkersRepository {
    suspend fun getAllMarkers(): Flow<List<Marker>>
}

class MarkersRepositoryImpl @Inject constructor(
    private val coordinateDao: CoordinateDao
): MarkersRepository {
    override suspend fun getAllMarkers(): Flow<List<Marker>> {
        return coordinateDao.getAllCoordinates().map { list ->
            list.map { coordinateEntity -> coordinateEntity.toMarkers() }
        }
    }
}

fun CoordinateEntity.toMarkers() = Marker("Точка $id", "$latitude°", "$longitude°")