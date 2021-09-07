package com.example.searchmovies.model.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NotesEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val idMovie: Int,
    val note: String
)