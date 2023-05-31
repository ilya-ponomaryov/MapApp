package com.example.mapapp.map

import com.example.mapapp.common.usecases.coordinates.CoordinatesGetter
import com.example.mapapp.common.usecases.coordinates.models.Coordinate
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import junit.framework.TestCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions

class MapViewModelTest {

    private lateinit var viewModel: MapViewModel

    @MockK
    lateinit var coordinatesGetter: CoordinatesGetter

    private val testCoordinatesList = listOf(
        Coordinate(40.7128, -74.0060),
        Coordinate(51.5074, -0.1278),
        Coordinate(35.6895, 139.6917),
    )

    private val testCoroutineDispatcher = TestCoroutineDispatcher()
    private val testCoroutineScope = TestCoroutineScope(testCoroutineDispatcher)

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(testCoroutineDispatcher)
        viewModel = MapViewModel(coordinatesGetter)
    }

    @After
    fun cleanup() {
        Dispatchers.resetMain()
        testCoroutineScope.cleanupTestCoroutines()
    }

    @Test
    fun `coordinates list should be empty initially`() {
        TestCase.assertTrue(viewModel.coordinatesList.value.isEmpty())
    }

    @Test
    fun `loadOnLaunch should fetch and emit coordinates list`() =
        runBlocking {
            coEvery { coordinatesGetter.invoke() } returns flowOf(testCoordinatesList)

            viewModel.loadOnLaunch()

            Assertions.assertEquals(viewModel.coordinatesList.value, testCoordinatesList)
        }

    @Test
    fun `updateLocations should fetch and emit coordinates list`() =
        runBlocking {
            coEvery { coordinatesGetter.invoke() } returns flowOf(testCoordinatesList)

            viewModel.updateLocations()

            Assertions.assertEquals(viewModel.coordinatesList.value, testCoordinatesList)
        }
}