package com.example.cinemalib.model.repository

import com.example.cinemalib.model.entities.MovieCard

class RepositoryImpl : Repository {
    override fun getMovieDatafromServer() = MovieCard()

}