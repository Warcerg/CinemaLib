package com.example.cinemalib.model.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieCard(
    val id: Int = 0,
    val title: String = "",
    val budget: Int = 0,
    val release_date: String = "",
    val revenue: Int = 0,
    val runtime: Int = 0,
    val plot_overview: String = "",
    val rating: Double = 0.0,
    val status: String = "",
    val poster: String = "",
    val adult: Boolean = false,
    var note: String = "",
    var cast: List<CastEntity> = emptyList()
) : Parcelable {

    fun toMovie(): Movie{
        return Movie(title,release_date,rating,id,poster,adult)
    }
}



