package com.example.cinemalib.model.received_data.person_data

import com.example.cinemalib.model.received_data.ApiUtils
import com.example.cinemalib.model.received_data.recieved_entities.PersonDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PersonDataAPI {


    @GET("{personID}?api_key=${ApiUtils.APIKEY}")
    fun getPersonDetails(
        @Path("personID") personID: String
    ): Call<PersonDTO>


}