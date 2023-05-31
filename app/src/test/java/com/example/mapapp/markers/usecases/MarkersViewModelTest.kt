package com.example.mapapp.markers.usecases

import com.example.mapapp.markers.MarkersViewModel
import com.example.mapapp.markers.models.Marker
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions


class MarkersViewModelTest {

    private val markersGetter = mockk<MarkersGetter>()

    private lateinit var viewModel: MarkersViewModel

    private val testMarkersList = listOf(
        Marker("Точка 1","40.7128", "-74.0060"),
        Marker("Точка 1","51.5074", "-0.1278"),
        Marker("Точка 1","35.6895", "139.6917"),
    )

    private val testCoroutineDispatcher = TestCoroutineDispatcher()
    private val testCoroutineScope = TestCoroutineScope(testCoroutineDispatcher)

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(testCoroutineDispatcher)
        viewModel = MarkersViewModel(markersGetter)
    }

    @After
    fun cleanup() {
        Dispatchers.resetMain()
        testCoroutineScope.cleanupTestCoroutines()
    }

    @Test
    fun `markers should be empty initially`() {
        assertTrue(viewModel.markers.value.isEmpty())
    }

    @Test
    fun `loadOnLaunch should fetch and emit markers list`() =
        runBlocking {
            coEvery { markersGetter.invoke() } returns flowOf(testMarkersList)

            viewModel.loadOnLaunch()

            Assertions.assertEquals(viewModel.markers.value, testMarkersList)
        }
}