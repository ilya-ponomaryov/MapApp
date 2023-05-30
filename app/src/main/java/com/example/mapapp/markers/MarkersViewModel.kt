package com.example.mapapp.markers

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MarkersViewModel @Inject constructor(
    private val markersGetter: MarkersGetter
) : ViewModel() {

    private val _markers = MutableStateFlow<List<Marker>>(listOf())
    val markers: StateFlow<List<Marker>> = _markers.asStateFlow()

    fun getMarkers() = viewModelScope.launch {
        markersGetter().collect { it -> _markers.tryEmit(it) }
    }
}