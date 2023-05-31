package com.example.mapapp.coordinates

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.mapapp.common.usecases.coordinates.models.Coordinate
import com.example.mapapp.coordinates.usecases.CoordinateSetter
import io.mockk.*
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class CoordinatesViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val setCoordinate: CoordinateSetter = mockk<CoordinateSetter>()

    private lateinit var viewModel: CoordinatesViewModel

    @Before
    fun setUp() {
        viewModel = CoordinatesViewModel(setCoordinate)
    }

    @Test
    fun `loadOnLaunch should update coordinates when valid`() {
        val latitude = "50.0"
        val longitude = "30.0"

        viewModel.loadOnLaunch(latitude, longitude)

        assertEquals(50.0, viewModel.latitude.value)
        assertEquals(30.0, viewModel.longitude.value)
    }

    @Test
    fun `latitudeDoAfterTextChanged should update latitude when valid`() {
        val latitude = "50.0"

        viewModel.latitudeDoAfterTextChanged(latitude)

        assertEquals(50.0, viewModel.latitude.value)
        assertEquals(false, viewModel.dismissDialog.value)
    }

    @Test
    fun `latitudeDoAfterTextChanged should emit toast message when invalid`() {
        val latitude = "invalid"

        viewModel.latitudeDoAfterTextChanged(latitude)

        assertEquals(false, viewModel.dismissDialog.value)
    }

    @Test
    fun `longitudeDoAfterTextChanged should update longitude when valid`() {
        val longitude = "30.0"

        viewModel.longitudeDoAfterTextChanged(longitude)

        assertEquals(30.0, viewModel.longitude.value, 0.0)
        assertEquals(false, viewModel.dismissDialog.value)
    }

    @Test
    fun `longitudeDoAfterTextChanged should emit toast message when invalid`() {
        val longitude = "invalid"

        viewModel.longitudeDoAfterTextChanged(longitude)

        assertEquals(false, viewModel.dismissDialog.value)
    }

    @Test
    fun `onSaveButtonClicked should save coordinate when both latitude and longitude are valid`() =
        runBlockingTest {
            coEvery { setCoordinate(Coordinate(0.0, 0.0)) } just runs

            viewModel.onSaveButtonClicked()

            setCoordinate(Coordinate(viewModel.latitude.value, viewModel.longitude.value))

            coVerify(exactly = 1) {
                setCoordinate(
                    eq(
                        Coordinate(
                            viewModel.latitude.value,
                            viewModel.longitude.value
                        )
                    )
                )
            }
        }
}