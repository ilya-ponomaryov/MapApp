package com.example.mapapp.map

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.View

class CursorView(context: Context) : View(context) {

    private val paint = Paint().apply {
        color = Color.RED
        strokeWidth = 5f
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.let {
            val x = width / 2f
            val y = height / 2f
            it.drawLine(x - 50, y, x + 50, y, paint)
            it.drawLine(x, y - 50, x, y + 50, paint)
        }
    }
}