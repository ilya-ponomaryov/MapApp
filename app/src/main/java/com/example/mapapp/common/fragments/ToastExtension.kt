package com.example.mapapp.common.fragments

import android.widget.Toast
import androidx.fragment.app.Fragment

fun Fragment.showToast(message: String, length: Int = Toast.LENGTH_SHORT) =
    context?.let {
        Toast.makeText(it, message.format(it), length).show()
    }