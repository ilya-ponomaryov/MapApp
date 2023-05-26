package com.example.mapapp

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
}