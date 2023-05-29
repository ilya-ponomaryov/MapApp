package com.example.mapapp

import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModel
import com.example.mapapp.coordinatesdialog.CoordinatesDialogFragment.Companion.ARG_LATITUDE
import com.example.mapapp.coordinatesdialog.CoordinatesDialogFragment.Companion.ARG_LONGITUDE
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor() : ViewModel() {

    private val _coordinate = MutableStateFlow(Coordinate(0.0, 0.0))
    private val coordinate = _coordinate.asStateFlow()

    fun setCoordinates(coordinate: Coordinate) {
        _coordinate.tryEmit(coordinate)
    }

    fun getBundle(): Bundle {
        val bundle = bundleOf()
        bundle.putString(ARG_LATITUDE, coordinate.value.latitude.toString())
        bundle.putString(ARG_LONGITUDE, coordinate.value.longitude.toString())
        return bundle
    }
}