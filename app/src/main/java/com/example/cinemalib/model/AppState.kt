package com.example.cinemalib.model

import com.example.cinemalib.model.entities.MovieCard

sealed class AppState {
    data class Success(val movieData: List<MovieCard>) : AppState()
    data class Error(val error: Throwable) : AppState()
    object Loading : AppState()

}