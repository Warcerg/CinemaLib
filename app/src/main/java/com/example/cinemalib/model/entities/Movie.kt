package com.example.cinemalib.model.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val title: String,
    val releaseDate: String,
    val rating: Double,
    val id: Int,
    val poster: String,
    val adult: Boolean
) : Parcelable {
    companion object {
    }
}
