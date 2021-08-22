package com.example.cinemalib.model.repository

import com.example.cinemalib.model.MovieListLoader
import com.example.cinemalib.model.entities.Movie


class RepositoryImpl : Repository {
    override fun getMovieDataFromServer(queryMovieList: String): List<Movie> {
        val dto = MovieListLoader.loadMovieList(queryMovieList)
        val movList = mutableListOf<Movie>()
        if (dto?.results != null) {
            for (result in dto?.results) {
                movList.add(
                    Movie(
                        title = result.title,
                        releaseDate = result.release_date,
                        rating = result.vote_average,
                        id = result.id
                    )
                )
            }
        }
        return movList.toList()
    }


    override fun getMovieFromLocalStorage() = Movie.getDefaultMovieList()

}