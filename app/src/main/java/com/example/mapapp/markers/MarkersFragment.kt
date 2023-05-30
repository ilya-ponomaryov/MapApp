package com.example.mapapp.markers

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.mapapp.common.fragments.BindingFragment
import com.example.mapapp.databinding.FragmentMarkersBinding

class MarkersFragment : BindingFragment<FragmentMarkersBinding>(
    FragmentMarkersBinding::inflate
) {

    private val viewModel: MarkersViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}