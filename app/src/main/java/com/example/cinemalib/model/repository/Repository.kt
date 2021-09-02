package com.example.cinemalib.model.repository

import com.example.cinemalib.model.entities.Movie
import com.example.cinemalib.model.entities.MovieCard

interface Repository {
    fun getMovieDataFromServer(queryMovieList: String): List<Movie>
    fun getMovieCardFromServer(movie_id: Int): MovieCard
    fun getAllHistory() : List<MovieCard>
    fun saveEntity(movieCard: MovieCard)
    fun getNoteEntity(movie_id: Int) : String
    fun isEntityExists(movie_id: Int) : Boolean
}