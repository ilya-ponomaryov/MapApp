package com.example.mapapp.coordinatesdialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import com.example.mapapp.databinding.FragmentCoordinatesBinding

class CoordinatesDialogFragment : DialogFragment() {

    private var _binding: FragmentCoordinatesBinding? = null
    private val binding get() = _binding!!

    private val latitudeArgs get() = arguments?.getString(ARG_LATITUDE)
    private val longitudeArgs get() = arguments?.getString(ARG_LONGITUDE)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)
        val view = FragmentCoordinatesBinding.inflate(layoutInflater, container, false)
        _binding = view
        return view.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupCoordinatesFields()
    }

    private fun setupCoordinatesFields() = with(binding) {
        latitude.setText(latitudeArgs)
        longitude.setText(longitudeArgs)
    }

    override fun onResume() {
        super.onResume()
        dialog?.window?.apply {
            val params = attributes
            params.width = ViewGroup.LayoutParams.MATCH_PARENT
            params.height = ViewGroup.LayoutParams.WRAP_CONTENT
            attributes = params
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        const val ARG_LATITUDE = "latitude"
        const val ARG_LONGITUDE = "longitude"
    }
}