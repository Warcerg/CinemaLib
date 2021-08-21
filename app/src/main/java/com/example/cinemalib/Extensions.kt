package com.example.cinemalib


import android.view.View
import androidx.viewbinding.ViewBinding
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.util.*


fun View.snackbarShow(resourceId: Int) {
    val snackbar = Snackbar.make(this, resources.getString(resourceId), Snackbar.LENGTH_LONG)
    snackbar.show()
}

fun ViewBinding.snackbarShow(resourceId: Int) {
    val snackbar = Snackbar.make(root, root.resources.getString(resourceId), Snackbar.LENGTH_LONG)
    snackbar.show()
}

fun ViewBinding.snackbarShow(text: String) {
    val snackbar = Snackbar.make(root, text, Snackbar.LENGTH_LONG)
    snackbar.show()
}

fun Date.formatToString(): String = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).format(this)



