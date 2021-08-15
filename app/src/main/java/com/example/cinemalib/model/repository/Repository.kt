package com.example.cinemalib.model.repository

import com.example.cinemalib.model.entities.MovieCard

interface Repository {
    fun getMovieDatafromServer(): MovieCard
}