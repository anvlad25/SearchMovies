package com.example.searchmovies.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class MoviesData(
    val movieName: String,
    val movieDesc: String,
    val movieDateFrom: Date,
    val movieRating: Int,
    val movieGroup: String
) : Parcelable