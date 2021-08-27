package com.example.searchmovies.model.gson_model.movie

data class MovieDTO(
    val id: Int = 0,
    val title: String = "Null",
    val release_date: String = "1900-01-01",
    val vote_average: Float = 0.0f,
    val overview: String = "Null",
    val poster_path: String = "Null",
    val genres: List<GenresDTO>
)