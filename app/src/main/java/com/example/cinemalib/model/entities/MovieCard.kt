package com.example.cinemalib.model.entities

data class MovieCard(
    val movie: Movie = getDefaultMovie(),
    val rating: Int = 0,
    val picture: Int = 0
)

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