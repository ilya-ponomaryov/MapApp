package com.example.mapapp

import android.os.Bundle
import android.view.View
import com.example.mapapp.common.fragments.BindingFragment
import com.example.mapapp.databinding.FragmentMapBinding
import com.yandex.mapkit.Animation
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MapFragment : BindingFragment<FragmentMapBinding>(
    FragmentMapBinding::inflate
) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MapKitFactory.initialize(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupMap()
    }

    private fun setupMap() = with(binding) {
        val cursorView = CursorView(requireContext())
        mapView.addView(cursorView)

        mapView.map.move(
            //Уточнить по поводу координат
            CameraPosition(Point(59.945933, 30.320045), 11.0f, 0.0f, 0.0f),
            Animation(Animation.Type.SMOOTH, 0f),
            null
        )
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
}