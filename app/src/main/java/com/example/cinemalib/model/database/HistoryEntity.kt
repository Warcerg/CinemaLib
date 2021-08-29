package com.example.cinemalib.model.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class HistoryEntity(
  @PrimaryKey(autoGenerate = true) val id: Long,
  val title : String,
  val releaseDate: String,
  val rating: Double,
  val movieID : Int,
  val poster: String,
  val adult: Boolean,
  val user_notes: String,
  val runtime: Int
)