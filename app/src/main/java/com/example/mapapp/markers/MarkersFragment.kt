package com.example.mapapp.markers

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.mapapp.common.flow.observe
import com.example.mapapp.common.fragments.BindingFragment
import com.example.mapapp.databinding.FragmentMarkersBinding
import com.example.mapapp.markers.adapters.MarkersItemDecorator
import com.example.mapapp.markers.adapters.MarkersRvAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MarkersFragment : BindingFragment<FragmentMarkersBinding>(
    FragmentMarkersBinding::inflate
) {

    private val viewModel: MarkersViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        viewModel.loadOnLaunch()
    }

    private fun setupRecyclerView() = with(binding) {
        val adapter = MarkersRvAdapter()
        val itemDecorator = MarkersItemDecorator(requireContext())

        markersRv.adapter = adapter
        markersRv.addItemDecoration(itemDecorator)

        viewModel.markers.observe(viewLifecycleOwner) {
            adapter.setItems(it)
        }
    }
}