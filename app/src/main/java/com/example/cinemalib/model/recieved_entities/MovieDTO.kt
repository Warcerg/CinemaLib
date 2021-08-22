package com.example.cinemalib.model.recieved_entities

data class MovieDTO (
    val title: String,
    val release_date: String,
/*    val release_year: String = release_date.subSequence(0,3) as String,*/
    val vote_average: Int,
    val id: Int
)