package com.example.searchmovies.model.gson_model.searchmovie

data class SearchResultDTO(
    val id: Int,
    val title: String,
    val overview: String,
    val poster_path: String = "/1.jpg"
)