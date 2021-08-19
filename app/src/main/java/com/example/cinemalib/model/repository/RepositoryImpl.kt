package com.example.cinemalib.model.repository

import com.example.cinemalib.model.entities.Movie
import com.example.cinemalib.model.entities.MovieCard


class RepositoryImpl : Repository {
    override fun getMovieDataFromServer() = MovieCard()

    override fun getMovieFromLocalStorage() = Movie.getDefaultMovieList()

}