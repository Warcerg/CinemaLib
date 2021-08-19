package com.example.cinemalib.model.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieCard(
    val movie: Movie = Movie.getDefaultMovie(),
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

