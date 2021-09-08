package com.example.cinemalib.model.repository

import com.example.cinemalib.model.database.Database
import com.example.cinemalib.model.database.HistoryEntity
import com.example.cinemalib.model.entities.CastEntity
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
                        poster = result.poster_path ?: "",
                        adult = result.adult ?: true
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
            poster = dto?.poster_path ?: "",
            adult = dto?.adult ?: true,
            cast = getMovieCast(movie_id)
        )
    }

    override fun getMovieCast(movie_id: Int): List<CastEntity> {
        val dto = MovieDataRepo.API.getMovieCast(movie_id.toString()).execute().body()
        val castList = mutableListOf<CastEntity>()
        if (dto?.cast != null){
            for (cast in dto.cast){
                castList.add(
                    CastEntity(
                        id = cast.id ?: 0,
                        name = cast.name ?: "name",
                        profilePath = cast.profilePath ?: "",
                        character = cast.character ?: "character",
                        /*placeOfBirth = getPlaceOfBirth(cast.id) ?: "Moscow"*/
                    )
                )
            }
        }
        return castList.toList()
    }

    private fun getPlaceOfBirth(id: Int): String {
        val dto = MovieDataRepo.APIperson.getPersonDetails(id.toString()).execute().body()
        return dto?.placeOfBirth ?: ""
    }


    override fun getAllHistory(): List<MovieCard> =
        convertHistoryEntityToMovieCard(Database.db.historyDao().all())

    override fun saveEntity(movieCard: MovieCard) {
        Database.db.historyDao().insert(convertMovieCardToEntity(movieCard))
    }

    override fun getNoteEntity(movie_id: Int): String {
        return Database.db.historyDao().getNoteDataByMovieId(movie_id)
    }

    override fun isEntityExists(movie_id: Int): Boolean {
        return Database.db.historyDao().isEntityExist(movie_id)
    }


    private fun convertHistoryEntityToMovieCard(entityList: List<HistoryEntity>): List<MovieCard> =
        entityList.map {
            MovieCard(
                it.movieId, it.title, it.budget, it.release_date,
                it.revenue, it.runtime, it.plot_overview, it.rating, it.status,
                it.poster, it.adult, it.note, getMovieCast(it.movieId)
            )
        }

    private fun convertMovieCardToEntity(movieCard: MovieCard): HistoryEntity =
        HistoryEntity(
            movieCard.id,
            movieCard.title,
            movieCard.budget,
            movieCard.release_date,
            movieCard.revenue,
            movieCard.runtime,
            movieCard.plot_overview,
            movieCard.rating,
            movieCard.status,
            movieCard.poster,
            movieCard.adult,
            movieCard.note
        )

}