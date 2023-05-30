package com.example.mapapp

import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mapapp.common.controlflow.SingleLaunch
import com.example.mapapp.common.usecases.CoordinatesGetter
import com.example.mapapp.common.usecases.models.Coordinate
import com.example.mapapp.coordinates.CoordinatesDialogFragment.Companion.ARG_LATITUDE
import com.example.mapapp.coordinates.CoordinatesDialogFragment.Companion.ARG_LONGITUDE
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
    private val coordinatesGetter: CoordinatesGetter
) : ViewModel() {

    private val _coordinate = MutableStateFlow(Coordinate(0.0, 0.0))
    private val coordinate = _coordinate.asStateFlow()

    private val _coordinatesList = MutableStateFlow(listOf<Coordinate>())
    val coordinatesList = _coordinatesList.asStateFlow()

    private val onlyLaunch = SingleLaunch()

    fun loadOnLaunch() = onlyLaunch {
        getCoordinates()
    }

    private fun getCoordinates() = viewModelScope.launch {
        coordinatesGetter().collect { it -> _coordinatesList.tryEmit(it) }
    }

    fun setCoordinate(coordinate: Coordinate) {
        _coordinate.tryEmit(coordinate)
    }

    fun getBundle(): Bundle {
        val bundle = bundleOf()
        bundle.putString(ARG_LATITUDE, coordinate.value.latitude.toString())
        bundle.putString(ARG_LONGITUDE, coordinate.value.longitude.toString())
        return bundle
    }

    fun updateLocations() {
        getCoordinates()
    }
}