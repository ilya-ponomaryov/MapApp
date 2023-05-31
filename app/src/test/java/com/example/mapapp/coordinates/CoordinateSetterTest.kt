package com.example.mapapp.coordinates

import com.example.mapapp.common.usecases.coordinates.CoordinateRepository
import com.example.mapapp.common.usecases.coordinates.models.Coordinate
import com.example.mapapp.coordinates.usecases.CoordinateSetterImpl
import io.mockk.*
import kotlinx.coroutines.runBlocking
import org.junit.Test

class CoordinateSetterImplTest {

    private val coordinateRepository = mockk<CoordinateRepository>()
    private val coordinateSetter = CoordinateSetterImpl(coordinateRepository)

    @Test
    fun `invoke should call saveCoordinate method with correct argument`() = runBlocking {
        val coordinate = Coordinate(0.0, 0.0)

        coEvery { coordinateRepository.saveCoordinate(Coordinate(0.0, 0.0)) } just runs

        coordinateSetter(coordinate)

        coVerify { coordinateRepository.saveCoordinate(coordinate) }
    }
}