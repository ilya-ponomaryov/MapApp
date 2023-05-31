package com.example.mapapp.markers.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mapapp.databinding.MarkerItemBinding
import com.example.mapapp.markers.models.Marker

class MarkersRvAdapter :
    RecyclerView.Adapter<MarkersRvAdapter.ViewHolder>() {

    private var markers = listOf<Marker>()

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

    fun setItems(newList: List<Marker>) {
        val result = DiffUtil.calculateDiff(MarkersDiffCallback(markers, newList))
        markers = newList
        result.dispatchUpdatesTo(this)
    }

    class MarkersDiffCallback(
        private val oldList: List<Marker>,
        private val newList: List<Marker>
    ) : DiffUtil.Callback() {

        override fun getOldListSize(): Int = oldList.size

        override fun getNewListSize(): Int = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList[newItemPosition]
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList[newItemPosition]
        }
    }
}