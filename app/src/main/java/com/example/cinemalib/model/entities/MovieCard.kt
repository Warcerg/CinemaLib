package com.example.cinemalib.model.entities

data class MovieCard(
    val movie: Movie = getDefaultMovie(),
    val rating: Int = 0,
    val picture: Int = 0
)

fun getDefaultMovie() = Movie("Some Movie", "1985", "Director director")
