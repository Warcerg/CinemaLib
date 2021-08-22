package com.example.cinemalib.model.repository

import com.example.cinemalib.model.MovieDetailsLoader
import com.example.cinemalib.model.MovieListLoader
import com.example.cinemalib.model.entities.Movie
import com.example.cinemalib.model.entities.MovieCard


class RepositoryImpl : Repository {
    override fun getMovieDataFromServer(queryMovieList: String): List<Movie> {
        val dto = MovieListLoader.loadMovieList(queryMovieList)
        val movList = mutableListOf<Movie>()
        if (dto?.results != null) {
            for (result in dto.results) {
                movList.add(
                    Movie(
                        title = result.title ?: "",
                        releaseDate = result.release_date ?: "",
                        rating = result.vote_average ?: 0.0,
                        id = result.id ?: 0
                    )
                )
            }
        }
        return movList.toList()
    }

    override fun getMovieCardFromServer(movie_id: Int): MovieCard {
        val dto = MovieDetailsLoader.loadMovieDetails(movie_id)
        return MovieCard(
            id = dto?.id ?: 0,
            title = dto?.title ?: "",
            budget = dto?.budget ?: 0,
            plot_overview = dto?.overview ?: "",
            rating = dto?.vote_average ?: 0.0,
            release_date = dto?.release_date ?: "0000",
            revenue = dto?.revenue ?: 0,
            runtime = dto?.runtime ?: 0,
            status = dto?.status ?: ""
        )
    }

}