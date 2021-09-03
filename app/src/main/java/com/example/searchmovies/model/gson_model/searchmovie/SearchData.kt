package com.example.searchmovies.model.gson_model.searchmovie

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SearchData(
    val id: Int = 0,
    val title: String = "Null",
    val overview: String = "Null",
    val poster_path: String? = "/1.jpg"
) : Parcelable