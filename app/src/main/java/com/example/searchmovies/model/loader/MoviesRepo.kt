package com.example.searchmovies.model.loader

import com.example.searchmovies.viewmodel.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MoviesRepo {
    val api: MoviesAPI by lazy {
        val adapter = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(ApiUtils.getOkHTTPBuilderWithHeaders())
            .build()

        adapter.create(MoviesAPI::class.java)
    }
}