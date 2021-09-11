package com.example.cinemalib.model

import com.example.cinemalib.model.entities.Movie
import com.example.cinemalib.model.entities.MovieCard

sealed class AppState {
    data class SuccessMovieLists(val movieData: List<List<Movie>>) : AppState()
    data class SuccessMovieCard(val movieCardData: MovieCard) : AppState()
    data class SuccessMovieCardHistory(val movieCardData: List<MovieCard>) : AppState()
    data class SuccessPlaceOfBirth(val placeOfBirth: String) : AppState()
    data class Error(val error: Throwable) : AppState()

}