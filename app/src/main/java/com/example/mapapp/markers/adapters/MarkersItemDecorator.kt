package com.example.mapapp.markers.adapters

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.mapapp.R

class MarkersItemDecorator(private val context: Context) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        val topSpace = context.resources.getDimensionPixelSize(R.dimen.eight_dp)
        val otherSpace = context.resources.getDimensionPixelSize(R.dimen.four_dp)

        val position = parent.getChildAdapterPosition(view)
        if (position == 0)
            outRect.top = topSpace

        outRect.left = otherSpace
        outRect.right = otherSpace
        outRect.bottom = otherSpace
    }
}