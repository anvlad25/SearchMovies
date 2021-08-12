package com.example.searchmovies.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MoviesData(val movieName: String, val movieDesc: String) : Parcelable