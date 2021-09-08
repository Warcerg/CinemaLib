package com.example.cinemalib.model.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CastEntity(
    val id: Int = 0,
    val name: String = "",
    val profilePath: String = "",
    val character: String = "",
    val placeOfBirth: String = ""
) : Parcelable {
}