package com.example.cinemalib.model.repository

import com.example.cinemalib.model.entities.Movie
import com.example.cinemalib.model.entities.MovieCard
import com.example.cinemalib.model.received_data.MovieDataRepo


class RepositoryImpl : Repository {
    override fun getMovieDataFromServer(queryMovieList: String): List<Movie> {
        val dto = MovieDataRepo.API.getMovieList(queryMovieList).execute().body()
        val movList = mutableListOf<Movie>()
        if (dto?.results != null) {
            for (result in dto.results) {
                movList.add(
                    Movie(
                        title = result.title ?: "",
                        releaseDate = result.release_date ?: "",
                        rating = result.vote_average ?: 0.0,
                        id = result.id ?: 0,
                        poster = result.poster_path ?: ""
                    )
                )
            }
        }
        return movList.toList()
    }

    override fun getMovieCardFromServer(movie_id: Int): MovieCard {
        val dto = MovieDataRepo.API.getMovieCard(movie_id.toString()).execute().body()
        return MovieCard(
            id = dto?.id ?: 0,
            title = dto?.title ?: "",
            budget = dto?.budget ?: 0,
            plot_overview = dto?.overview ?: "",
            rating = dto?.vote_average ?: 0.0,
            release_date = dto?.release_date ?: "0000",
            revenue = dto?.revenue ?: 0,
            runtime = dto?.runtime ?: 0,
            status = dto?.status ?: "",
            poster = dto?.poster_path ?: ""
        )
    }

}