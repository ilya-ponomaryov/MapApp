package com.example.mapapp.markers.usecases

import com.example.mapapp.markers.models.Marker
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface MarkersGetter {
    suspend operator fun invoke(): Flow<List<Marker>>
}

class MarkersGetterImpl @Inject constructor(
private val repository: MarkersRepository
) : MarkersGetter {
    override suspend fun invoke(): Flow<List<Marker>> = repository.getAllMarkers()
}