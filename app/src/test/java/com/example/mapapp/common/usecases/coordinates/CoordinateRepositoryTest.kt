package com.example.mapapp.common.usecases.coordinates

import com.example.mapapp.common.storages.CoordinateDao
import com.example.mapapp.common.storages.models.CoordinateEntity
import com.example.mapapp.common.usecases.coordinates.models.Coordinate
import io.mockk.*
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class CoordinateRepositoryImplTest {
    private lateinit var repository: CoordinateRepository
    private lateinit var coordinateDao: CoordinateDao

    @Before
    fun setup() {
        coordinateDao = mockk()
        repository = CoordinateRepositoryImpl(coordinateDao)
    }

    @Test
    fun saveCoordinate_ShouldCallInsertCoordinate() = runBlocking {
        val coordinate = Coordinate(55.7558, 37.6173)
        val coordinateEntity = coordinate.toCoordinateEntity()
        coEvery { coordinateDao.insertCoordinate(coordinateEntity) } just Runs

        repository.saveCoordinate(coordinate)

        coVerify { coordinateDao.insertCoordinate(coordinateEntity) }
    }

    @Test
    fun getAllCoordinates_ShouldReturnListOfCoordinates() = runBlocking {
        val coordinateEntityList = listOf(
            CoordinateEntity(0, 11.11, 12.12),
            CoordinateEntity(1, 30.33, 13.12)
        )
        val coordinateList = coordinateEntityList.map { it.toCoordinate() }
        val flow = flowOf(coordinateEntityList)
        every { coordinateDao.getAllCoordinates() } returns flow

        val result = repository.getAllCoordinates().toList()

        assertEquals(coordinateList, result.first())
    }
}