package com.example.mapapp.common.usecases.coordinates

import com.example.mapapp.common.usecases.coordinates.models.Coordinate
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test

class CoordinatesGetterTest {

    private val repository = mockk<CoordinateRepository>()
    private lateinit var coordinatesGetter: CoordinatesGetter

    @Before
    fun setup() {
        coordinatesGetter = CoordinatesGetterImpl(repository)
    }

    @Test
    fun `invoke should return flow from repository`() = runBlockingTest {
        val expectedCoordinatesFlow = flowOf(listOf(Coordinate(0.0, 0.0)))
        coEvery { repository.getAllCoordinates() } returns expectedCoordinatesFlow

        val actualCoordinatesFlow = coordinatesGetter.invoke()

        assertEquals(expectedCoordinatesFlow.toList(), actualCoordinatesFlow.toList())
    }

    @Test
    fun `invoke should call getAllCoordinates once`() = runBlockingTest {
        val expectedCoordinatesFlow = flowOf(listOf(Coordinate(0.0, 0.0)))
        coEvery { repository.getAllCoordinates() } returns expectedCoordinatesFlow

        coordinatesGetter.invoke().toList()

        coVerify(exactly = 1) { repository.getAllCoordinates() }
    }
}