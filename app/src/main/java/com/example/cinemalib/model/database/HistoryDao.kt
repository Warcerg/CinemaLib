package com.example.cinemalib.model.database

import androidx.room.*


@Dao
interface HistoryDao {

    @Query("SELECT * FROM HistoryEntity")
    fun all(): List<HistoryEntity>

    @Query("SELECT * FROM HistoryEntity WHERE movieID = :movieId")
    fun getDataByMovieId(movieId: Int): List<HistoryEntity>

    @Query("SELECT * FROM HistoryEntity WHERE adult = :adult")
    fun getNonAdultContent(adult: Boolean): List<HistoryEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(entity: HistoryEntity)

    @Update
    fun update(entity: HistoryEntity)

    @Delete
    fun delete(entity: HistoryEntity)

    @Query("DELETE FROM HistoryEntity WHERE movieID = :movieId")
    fun deleteByMovieId(movieId: Int)
}