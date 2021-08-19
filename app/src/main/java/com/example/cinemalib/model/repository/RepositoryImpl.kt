package com.example.cinemalib.model.repository

import com.example.cinemalib.model.entities.MovieCard
import com.example.cinemalib.model.entities.getDefaultMovieList

class RepositoryImpl : Repository {
    override fun getMovieDatafromServer() = MovieCard()

    override fun getMovieFromLocalStorage() = getDefaultMovieList()

}