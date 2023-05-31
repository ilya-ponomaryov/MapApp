package com.example.mapapp.markers.usecases

import com.example.mapapp.markers.models.Marker
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals

@ExperimentalCoroutinesApi
class MarkersGetterImplTest {

    private val mockRepository = object : MarkersRepository {
        override suspend fun getAllMarkers(): Flow<List<Marker>> =
            flow { emit(listOf(Marker("Test Marker", "66.66", "77.77"))) }
    }

    private val markersGetter: MarkersGetter = MarkersGetterImpl(mockRepository)

    @Test
    fun `when invoke method is called, then it should return a list of markers`() = runBlocking {
        val markers = mutableListOf<Marker>()
        markersGetter().collect { markers.addAll(it) }
        assertEquals(1, markers.size)
    }
}