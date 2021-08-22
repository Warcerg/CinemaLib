package com.example.cinemalib.model

import com.example.cinemalib.model.entities.Movie
import com.example.cinemalib.model.entities.MovieCard

sealed class AppState {
    data class SuccessMovieList(val movieData: List<Movie>) : AppState()
    data class SuccessMovieCard(val movieCardData: MovieCard) : AppState()
    data class Error(val error: Throwable) : AppState()

}