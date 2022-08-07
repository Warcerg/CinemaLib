package com.example.cinemalib

import com.example.cinemalib.model.entities.CastEntity
import com.example.cinemalib.model.entities.MovieCard
import com.example.cinemalib.model.repository.RepositoryImpl
import org.junit.Assert
import org.junit.Test

class RepositoryImplTest {

    private val repository = RepositoryImpl()

    @Test
    fun repositoryImpl_NotNullMovieCardFetchFromServer_ReturnsTrue(){
        Assert.assertNotNull(repository.getMovieCardFromServer(766507))
    }

    @Test
    fun repositoryImpl_EqualMovieCardFetchFromServer_ReturnsFalse(){
        val mc = MovieCard(
            766507,
            "Prey",
            0,
            "2022-07-21",
            0,
            100,
            "In the Comanche Nation in 1717, a fierce and highly skilled warrior named Naru learns the prey she is stalking is a highly evolved alien with a technologically advanced arsenal.",
            8.331,
            "Released",
            "/h58U7d1OZyuWaCWMYvw4mfnv6H3.jpg",
            false,
            "",
            emptyList()
        )
        Assert.assertNotEquals(mc, repository.getMovieCardFromServer(766507))
    }

    @Test
    fun repositoryImpl_EmptyMovieCardFetchFromServer_ReturnsTrue(){
        val mc = MovieCard(
            0,
            "",
            0,
            "0000",
            0,
            0,
            "",
            0.0,
            "",
            "",
            true,
            "",
            emptyList()
        )
        Assert.assertEquals(mc, repository.getMovieCardFromServer(0))
    }

    @Test
    fun repositoryImpl_NowPlayingMoviesEqual_ReturnTrue(){
        val movList = repository.getNowPlayingMovieList()
        Assert.assertArrayEquals(movList, repository.getMovieArrayDataFromServer("now_playing"))
    }

    @Test
    fun repositoryImpl_NullMovieCast_ReturnTrue(){
        Assert.assertNull(repository.getMovieCastN(0))
    }

    @Test
    fun repositoryImpl_SameTopRatedMoviesList_ReturnTrue(){
        val movList = repository.getTopRatedMovieList()
        Assert.assertArrayEquals(movList, repository.getMovieArrayDataFromServer("top_rated"))
    }
}
