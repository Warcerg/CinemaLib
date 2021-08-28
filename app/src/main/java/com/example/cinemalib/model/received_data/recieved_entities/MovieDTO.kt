package com.example.cinemalib.model.received_data.recieved_entities

data class MovieDTO(
    val title: String?,
    val release_date: String?,
    val vote_average: Double?,
    val id: Int?,
    val poster_path: String?
)