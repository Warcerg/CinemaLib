package com.example.cinemalib.model.received_data.person_data

import com.example.cinemalib.model.received_data.ApiUtils
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object PersonDataRepo {

    val API: PersonDataAPI by lazy {
        val adapter = Retrofit.Builder()
            .baseUrl(ApiUtils.baseUrlPerson)
            .addConverterFactory(GsonConverterFactory.create())
            .client(ApiUtils.getOkHTTPBuilder())
            .build()

        adapter.create(PersonDataAPI::class.java)
    }
}