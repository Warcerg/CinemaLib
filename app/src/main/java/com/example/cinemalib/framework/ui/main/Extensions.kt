package com.example.cinemalib

import android.view.View
import android.widget.TextView
import androidx.viewbinding.ViewBinding
import com.google.android.material.snackbar.Snackbar


fun View.snackbarShow(resourceId : Int) {
    val snackbar =  Snackbar.make(this, resources.getString(resourceId), Snackbar.LENGTH_LONG)
    snackbar.show()
}

fun ViewBinding.snackbarShow(resourceId : Int) {
    val snackbar =  Snackbar.make(root, root.resources.getString(resourceId), Snackbar.LENGTH_LONG)
    snackbar.show()
}



