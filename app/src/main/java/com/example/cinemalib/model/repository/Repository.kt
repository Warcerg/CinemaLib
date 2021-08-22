package com.example.cinemalib.model.repository

import com.example.cinemalib.model.entities.Movie
import com.example.cinemalib.model.entities.MovieCard

interface Repository {
    fun getMovieDataFromServer(queryMovieList: String): List<Movie>
    fun getMovieFromLocalStorage() : List<MovieCard>
}