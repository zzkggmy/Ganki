package com.example.kai.ganki.utils

import android.annotation.SuppressLint
import android.widget.Toast

object Toast {
    @SuppressLint("ShowToast")
    fun showShortToast(text: String) {
        Toast.makeText(Common.context, text, Toast.LENGTH_SHORT).show()
    }

    @SuppressLint("ShowToast")
    fun showLongToast(text: String) {
        Toast.makeText(Common.context, text, Toast.LENGTH_LONG).show()
    }

}