package com.example.mapapp.common.navigation

import android.os.Bundle
import androidx.annotation.IdRes
import com.example.mapapp.R
import com.example.mapapp.common.navigation.CoordinatesParameters.Companion.COORDINATES_KEY
import java.io.Serializable

sealed class NavArgs(
    @IdRes val action: Int,
    val data: Bundle? = null
)

class NavigateToCoordinatesDialogFragmentFromMapFragment(params: CoordinatesParameters) :
    NavArgs(R.id.action_mapFragment_to_coordinatesDialogFragment, params.toBundle())

private fun CoordinatesParameters.toBundle(): Bundle = Bundle()
    .apply { putSerializable(COORDINATES_KEY, this@toBundle) }

data class CoordinatesParameters(
    val latitude: Double,
    val longitude: Double
) : Serializable {
    companion object {
        const val COORDINATES_KEY = "COORDINATES_KEY"
    }
}