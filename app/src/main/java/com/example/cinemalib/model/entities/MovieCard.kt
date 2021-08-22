package com.example.cinemalib.model.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieCard(
    val id: Int,
    val title: String,
    val budget: Int,
    val release_date: String,
    val release_year: String = release_date.subSequence(0,4).toString(),
    val revenue: Int,
    val runtime: Int,
    val plot_overview: String?,
    val rating: Double,
    val status: String) : Parcelable

