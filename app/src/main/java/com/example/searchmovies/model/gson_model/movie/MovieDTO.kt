package com.example.searchmovies.model.gson_model.movie

data class MovieDTO(
    val id: Int = 0,
    val title: String = "Null",
    val release_date: String = "1900-01-01",
    val vote_average: Float = 0.0f,
    val overview: String = "Null",
    val poster_path: String = "/1.jpg",
    val genres: List<GenresDTO>
)