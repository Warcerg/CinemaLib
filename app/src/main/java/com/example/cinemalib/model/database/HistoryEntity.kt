package com.example.cinemalib.model.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class HistoryEntity(
  @PrimaryKey(autoGenerate = false) val movieId: Int,
  val title: String,
  val budget: Int,
  val release_date: String,
  val revenue: Int,
  val runtime: Int,
  val plot_overview: String,
  val rating: Double,
  val status: String,
  val poster: String,
  val adult: Boolean,
  val note: String
)