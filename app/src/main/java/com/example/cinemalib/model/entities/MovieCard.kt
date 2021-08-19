package com.example.cinemalib.model.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieCard(
    val movie: Movie = getDefaultMovie(),
    val rating: Int = 0,
    val picture: Int = 0,
    val gross: String = "$450 525 000",
    val trivia: String = "The famous ${movie.director} started to write the script for this movie when he was 10. " +
            "\n Also it is not wildly known - but ${movie.director} funded the movie all by himself " +
            "(I should note that we're talking about ${movie.budget} here). " +
            "He made money to fund the movie by selling lemonade to his neighbours in the summer starting when he was 11." +
            "\n Despite the niche theme, the movie became a worldwide phenomenon and grossed $gross.",
    val genre: String = "Drama, Thriller, Comedy, Sci-fi"
): Parcelable

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