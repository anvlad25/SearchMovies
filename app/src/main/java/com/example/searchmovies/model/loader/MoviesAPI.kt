package com.example.searchmovies.model.loader

import com.example.searchmovies.model.gson_model.movie.MovieDTO
import com.example.searchmovies.model.gson_model.trending.TrendingDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesAPI {
    @GET("trending/movie/week")
    fun getTrending(
        @Query("api_key") api_key: String,
        @Query("language") language: String = "ru"
    ) : Call<TrendingDTO>

    @GET("movie/{id}")
    fun getMovie(
        @Path("id") id: Int,
        @Query("api_key") api_key: String,
        @Query("language") language: String = "ru"
    ) : Call<MovieDTO>
}