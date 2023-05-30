package com.example.mapapp.markers

import androidx.lifecycle.ViewModel
import com.example.mapapp.common.controlflow.SingleLaunch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MarkersViewModel : ViewModel() {

    private val _markers = MutableStateFlow<List<Marker>>(listOf())
    val markers: StateFlow<List<Marker>> = _markers.asStateFlow()

    private val onlyLaunch = SingleLaunch()

    fun loadOnLaunch() = onlyLaunch {
        getMarkers()
    }

    private fun getMarkers() {
        val markers = arrayListOf<Marker>(
            Marker("Точка 0", "55.66°", "66.77°"),
            Marker("Точка 1", "55.66°", "66.77°"),
            Marker("Точка 2", "55.66°", "66.77°"),
            Marker("Точка 3", "55.66°", "66.77°"),
            Marker("Точка 4", "55.66°", "66.77v"),
        )
        _markers.value = markers
    }
}