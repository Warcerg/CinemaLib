package com.example.cinemalib.model.received_data.movie_data

import com.example.cinemalib.model.received_data.ApiUtils
import com.example.cinemalib.model.received_data.recieved_entities.CastListDTO
import com.example.cinemalib.model.received_data.recieved_entities.MovieDetailsDTO
import com.example.cinemalib.model.received_data.recieved_entities.MovieListDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieDataAPI {

    @GET("{requestType}?api_key=${ApiUtils.APIKEY}")
    fun getMovieList(
        @Path("requestType") requestType: String
    ): Call<MovieListDTO>

    @GET("{movieID}?api_key=${ApiUtils.APIKEY}")
    fun getMovieCard(
        @Path("movieID") movieID: String
    ): Call<MovieDetailsDTO>

    @GET("{movieID}/credits?api_key=${ApiUtils.APIKEY}")
    fun getMovieCast(
        @Path("movieID") movieID: String
    ): Call<CastListDTO>


}