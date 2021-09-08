package com.example.cinemalib.model.received_data.recieved_entities

import com.google.gson.annotations.SerializedName

data class PersonDTO (
    @SerializedName("place_of_birth")
    val placeOfBirth: String?
        )
