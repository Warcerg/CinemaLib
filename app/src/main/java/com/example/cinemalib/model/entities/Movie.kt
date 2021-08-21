package com.example.cinemalib.model.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.text.SimpleDateFormat
import java.util.*

@Parcelize
data class Movie(
    val title: String,
    val year: String,
    val director: String,
/*    val releaseDate: String = "01.10.$year",*/
    val releaseDate: Date = SimpleDateFormat(
        "dd.MM.yyyy",
        Locale.getDefault()
    ).parse("01.10.$year")!!,
    val synopsis: String = "The plot of the movie $title consist of drama, comedy and suspense." +
            " The year is $year and our heroes found themselves in danger...from outer space." +
            " \n Some other random text about movie plot. " +
            "\n Some more random nonsense text about the plot and what the viewers might" +
            " to expect from this masterpiece from $director",
    val budget: String = "$150 000 000"
) : Parcelable {
    companion object {
        fun getDefaultMovie() = Movie("Some Movie", "1985", "Director director")

        fun getDefaultMovieList() = listOf(
            MovieCard(Movie("Jurassic Park", "1993", "Steven Spielberg"), 8, 0),
            MovieCard(Movie("Pulp Fiction", "1994", "Quentin Tarantino"), 9, 0),
            MovieCard(Movie("Goodfellas", "1990", "Martin Scorsese"), 9, 0),
            MovieCard(Movie("Fight Club", "1999", "David Fincher"), 9, 0),
            MovieCard(Movie("The Big Lebowski", "1998", "Joel & Ethan Coen"), 8, 0),
            MovieCard(Movie("Fargo", "1996", "Joel & Ethan Coen"), 8, 0),
            MovieCard(Movie("Magnolia", "1999", "Paul Thomas Anderson"), 8, 0),
            MovieCard(Movie("Se7en", "1995", "David Fincher"), 9, 0),
            MovieCard(Movie("Boogie Nights", "1997", "Paul Thomas Anderson"), 8, 0),
            MovieCard(Movie("Reservoir Dogs", "1992", "Quentin Tarantino"), 8, 0),
            MovieCard(Movie("The Shawshank Redemption", "1994", "Frank Darabont"), 9, 0),
        )
    }
}
