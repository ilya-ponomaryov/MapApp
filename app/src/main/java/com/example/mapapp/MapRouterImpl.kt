package com.example.mapapp

import com.example.mapapp.common.navigation.CoordinatesParameters
import com.example.mapapp.common.navigation.NavigateToCoordinatesDialogFragmentFromMapFragment
import com.example.mapapp.common.navigation.NavigationFlow

interface MapRouter {
    fun openCoordinatesDialog(latitude: Double, longitude: Double)
}

class MapRouterImpl(
    private val navigationFlow: NavigationFlow
) : MapRouter {
    override fun openCoordinatesDialog(latitude: Double, longitude: Double) {
        val coordinatesParameters = CoordinatesParameters(latitude, longitude)

        val navigate = NavigateToCoordinatesDialogFragmentFromMapFragment(coordinatesParameters)
        navigationFlow.tryEmit(navigate)
    }
}