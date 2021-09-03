package com.example.searchmovies.model.database

import androidx.room.*

@Dao
interface NotesDao {
    @Query("SELECT * FROM NotesEntity")
    fun all(): List<NotesEntity>

    @Query("SELECT * FROM NotesEntity WHERE idMovie = :idMovie")
    fun getDataByIdMovie(idMovie: Int): List<NotesEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(entity: NotesEntity)

    @Update
    fun update(entity: NotesEntity)

    @Delete
    fun delete(entity: NotesEntity)

    @Query("DELETE FROM NotesEntity WHERE idMovie = :idMovie")
    fun deleteByIdMovie(idMovie: Int)
}