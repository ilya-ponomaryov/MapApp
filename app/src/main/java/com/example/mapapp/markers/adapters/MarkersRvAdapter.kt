package com.example.mapapp.markers.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mapapp.databinding.MarkerItemBinding
import com.example.mapapp.markers.Marker

class MarkersRvAdapter :
    RecyclerView.Adapter<MarkersRvAdapter.ViewHolder>() {

    private val markers = arrayListOf<Marker>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        MarkerItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )

    override fun getItemCount(): Int = markers.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(markers[position])
    }

    inner class ViewHolder(private val binding: MarkerItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(marker: Marker) = with(binding) {
            name.text = marker.name
            latitude.text = marker.latitude
            longitude.text = marker.longitude
        }
    }

    fun setItems(data: List<Marker>) {
        markers.addAll(data)
    }
}