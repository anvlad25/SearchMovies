package com.example.searchmovies.model.gson_model.movie

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MoviesData(
    val id: Int = 0,
    val movieName: String = "Null",
    val movieDesc: String = "Null",
    val movieDateFrom: String = "1900-01-01",
    val movieRating: Float = 0.0f,
    val movieGroup: String = "Null",
    val poster_path: String = "Null",
    var favorite: Boolean = false
) : Parcelable