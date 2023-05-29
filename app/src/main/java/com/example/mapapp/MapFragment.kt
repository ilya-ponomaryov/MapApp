package com.example.mapapp

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.mapapp.common.ToolbarMenuClickListener
import com.example.mapapp.common.fragments.BindingFragment
import com.example.mapapp.databinding.FragmentMapBinding
import com.yandex.mapkit.Animation
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraListener
import com.yandex.mapkit.map.CameraPosition
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MapFragment : BindingFragment<FragmentMapBinding>(
    FragmentMapBinding::inflate
), ToolbarMenuClickListener {

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

    override fun onResume() {
        super.onResume()
        observeCameraMove()
    }

    private fun observeCameraMove() {
        cameraListener = CameraListener { _, cameraPosition, _, _ ->
            viewModel.setCoordinates(
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
        navigation.navigate(
            R.id.action_mapFragment_to_coordinatesDialogFragment,
            viewModel.getBundle()
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.mapView.map.removeCameraListener(cameraListener)
        MapKitFactory.getInstance().onStop()
    }
}
