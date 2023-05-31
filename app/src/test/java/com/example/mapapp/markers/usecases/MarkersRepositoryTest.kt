package com.example.mapapp.markers.usecases

import com.example.mapapp.common.storages.CoordinateDao
import com.example.mapapp.common.storages.models.CoordinateEntity
import com.example.mapapp.markers.models.Marker
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class MarkersRepositoryTest {

    private lateinit var coordinateDao: CoordinateDao
    private lateinit var repository: MarkersRepository

    @Before
    fun setUp() {
        coordinateDao = mockk()
        repository = MarkersRepositoryImpl(coordinateDao)
    }

    @Test
    fun `test getAllMarkers`() = runTest {

        val coordinateList = listOf(
            CoordinateEntity(1, 123.456, 234.567),
            CoordinateEntity(2, 456.789, 678.901)
        )
        val expectedList = listOf(
            Marker("Точка 1", "123.456°", "234.567°"),
            Marker("Точка 2", "456.789°", "678.901°")
        )
        coEvery { coordinateDao.getAllCoordinates() } returns flowOf(coordinateList)

        val result = repository.getAllMarkers().first()

        assertEquals(expectedList, result)
        coVerify(exactly = 1) { coordinateDao.getAllCoordinates() }
        confirmVerified(coordinateDao)
    }
}