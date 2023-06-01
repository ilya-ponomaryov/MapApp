package com.example.mapapp.map

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.mapapp.R
import com.example.mapapp.common.listeners.DialogDismissListener
import com.example.mapapp.common.listeners.ToolbarMenuClickListener
import com.example.mapapp.common.flow.observe
import com.example.mapapp.common.fragments.BindingFragment
import com.example.mapapp.common.usecases.coordinates.models.Coordinate
import com.example.mapapp.coordinates.CoordinatesDialogFragment
import com.example.mapapp.databinding.FragmentMapBinding
import com.yandex.mapkit.Animation
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraListener
import com.yandex.mapkit.map.CameraPosition
import com.yandex.runtime.ui_view.ViewProvider
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MapFragment : BindingFragment<FragmentMapBinding>(
    FragmentMapBinding::inflate
), ToolbarMenuClickListener, DialogDismissListener {

    private val viewModel: MapViewModel by viewModels()

    private lateinit var cameraListener: CameraListener

    private lateinit var navigation: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MapKitFactory.initialize(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navigation = findNavController()

        setupMap()
        setupMarkers()
        viewModel.loadOnLaunch()
    }

    private fun setupMap() = with(binding) {
        val cursorView = CursorView(requireContext())
        mapView.addView(cursorView)

        mapView.map.move(
            CameraPosition(Point(59.945933, 30.320045), 11.0f, 0.0f, 0.0f),
            Animation(Animation.Type.SMOOTH, 0f),
            null
        )
    }

    private fun setupMarkers() {
        viewModel.coordinatesList.observe(viewLifecycleOwner) {
            it.forEach { coordinate ->
                createMarker(coordinate)
            }
        }
    }

    private fun createMarker(coordinate: Coordinate) {
        val view = View(requireContext()).apply {
            background = AppCompatResources.getDrawable(
                context, R.drawable.baseline_location_on_24
            )
        }

        binding.mapView.map.mapObjects.addPlacemark(
            Point(coordinate.latitude, coordinate.longitude),
            ViewProvider(view)
        )

    }

    override fun onResume() {
        super.onResume()
        observeCameraMove()
    }

    private fun observeCameraMove() {
        cameraListener = CameraListener { _, cameraPosition, _, _ ->
            viewModel.setCoordinate(
                Coordinate(
                    cameraPosition.target.latitude, cameraPosition.target.longitude
                )
            )
        }
        binding.mapView.map.addCameraListener(cameraListener)
    }

    override fun onStart() {
        super.onStart()
        MapKitFactory.getInstance().onStart()
        binding.mapView.onStart()
    }

    override fun onStop() {
        super.onStop()
        MapKitFactory.getInstance().onStop()
        binding.mapView.onStop()
    }

    override fun onToolbarMenuItemClicked(item: MenuItem) {
        val dialog = CoordinatesDialogFragment()
        dialog.arguments = viewModel.getBundle()
        dialog.show(parentFragmentManager)
    }

    override fun onDialogDismissed() {
        viewModel.updateLocations()
    }

    override fun onDestroy() {
        super.onDestroy()
        MapKitFactory.getInstance().onStop()
    }
}
