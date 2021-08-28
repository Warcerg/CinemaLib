package com.example.cinemalib.model.received_data

import com.example.cinemalib.model.received_data.recieved_entities.MovieDetailsDTO
import com.example.cinemalib.model.received_data.recieved_entities.MovieListDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieDataAPI {
    @GET("{requestType}?api_key=cb37ac22ea3216d2a5291d87e4c14152")
    fun getMovieList(
        @Path("requestType") requestType : String
/*        @Query ("apikey") apikey: String*/
    ) : Call<MovieListDTO>

    @GET("{requestType}?api_key=cb37ac22ea3216d2a5291d87e4c14152")
    fun getMovieCard(
        @Path("requestType") requestType : String
    ) : Call<MovieDetailsDTO>


}