package com.example.cinemalib.model.received_data.recieved_entities

import com.google.gson.annotations.SerializedName

class CastEntityDTO(
    val id: Int?,
    val name: String?,
    @SerializedName("profile_path")
    val profilePath: String?,
    val character: String?
) {
}