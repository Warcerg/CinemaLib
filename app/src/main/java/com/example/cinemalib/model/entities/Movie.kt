package com.example.cinemalib.model.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class Movie (
    val title: String,
    val year: String,
    val director: String,
    val releaseDate: String = "01.10.$year",
    val synopsis: String = "The plot of the movie $title consist of drama, comedy and suspense." +
            " The year is $year and our heroes found themselves in danger...from outer space." +
            " \n Some other random text about movie plot. " +
            "\n Some more random nonsense text about the plot and what the viewers might" +
            " to expect from this masterpiece from $director",
    val budget: String = "$150 000 000"
    ): Parcelable
