package com.example.searchmovies.model.gson_model.trending

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class MoviesTrendingData(
    val id: Int,
    val title: String,
    val poster_path: String = "Null",
) : Parcelable