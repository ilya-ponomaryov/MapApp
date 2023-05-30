package com.example.mapapp.markers

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