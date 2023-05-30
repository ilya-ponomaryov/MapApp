package com.example.mapapp.coordinates

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mapapp.common.controlflow.SingleLaunch
import com.example.mapapp.common.flow.MutableSingleEventFlow
import com.example.mapapp.common.usecases.CoordinateSetter
import com.example.mapapp.common.usecases.models.Coordinate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoordinatesViewModel @Inject constructor(
    private val setCoordinate: CoordinateSetter
) : ViewModel() {

    private val _latitude = MutableStateFlow(0.0)
    val latitude = _latitude.asStateFlow()

    private val _longitude = MutableStateFlow(0.0)
    val longitude = _longitude.asStateFlow()

    private var _isValidLatitude = MutableStateFlow(false)
    val isValidLatitude = _isValidLatitude

    private var _isValidLongitude = MutableStateFlow(false)
    val isValidLongitude = _isValidLongitude

    private var _dismissDialog = MutableStateFlow(false)
    val dismissDialog = _dismissDialog

    private val _toastMessage = MutableSingleEventFlow<String>()
    val toastMessage = _toastMessage.asSharedFlow()

    private val onlyLaunch = SingleLaunch()

    fun loadOnLaunch(latitude: String, longitude: String) = onlyLaunch {
        updateCoordinate(latitude, longitude)
    }

    private fun updateCoordinate(latitude: String, longitude: String) {
        if (validateLatitude(latitude.toDouble()) && validateLongitude(longitude.toDouble())) {
            _latitude.value = latitude.toDouble()
            _longitude.value = longitude.toDouble()
        } else {
            _toastMessage.tryEmit("Некорректные координаты")
        }
    }

    fun latitudeDoAfterTextChanged(latitude: String) {
        if (!validateLatitude(latitude.toDouble())) {
            _toastMessage.tryEmit("Широта введена неверно!")
            _dismissDialog.value = false
        } else {
            _latitude.value = latitude.toDouble()
        }
    }

    fun longitudeDoAfterTextChanged(longitude: String) {
        if (!validateLongitude(longitude.toDouble())) {
            _toastMessage.tryEmit("Долгота введена неверно!")
            _dismissDialog.value = false
        } else {
            _longitude.value = longitude.toDouble()
        }
    }

    fun onSaveButtonClicked() {
        if (isValidLatitude.value && isValidLongitude.value)
            saveCoordinate()
        else
            _toastMessage.tryEmit("Данные введены неверно. Сохранение недоступно!")

    }

    private fun saveCoordinate() = viewModelScope.launch {
        setCoordinate(Coordinate(latitude.value, longitude.value))
        _dismissDialog.value = true
    }

    private fun validateLatitude(latitude: Double): Boolean {
        val isValid = latitude in -90.0..90.0
        _isValidLatitude.value = isValid
        return isValid

    }

    private fun validateLongitude(longitude: Double): Boolean {
        val isValid = longitude in -180.0..180.0
        _isValidLongitude.value = isValid
        return isValid
    }
}