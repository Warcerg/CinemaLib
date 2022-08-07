package com.example.cinemalib.model.received_data.movie_data

import com.example.cinemalib.model.received_data.ApiUtils
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MovieDataRepo {
    val API: MovieDataAPI by lazy {
        val adapter = Retrofit.Builder()
            .baseUrl(ApiUtils.baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(ApiUtils.getOkHTTPBuilder())
            .build()

        adapter.create(MovieDataAPI::class.java)
    }

}