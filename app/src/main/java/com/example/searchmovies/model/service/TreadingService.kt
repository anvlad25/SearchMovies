package com.example.searchmovies.model.service

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import com.example.searchmovies.model.gson_model.trending.MoviesTrendingData
import com.example.searchmovies.model.gson_model.trending.TrendingDTO
import com.example.searchmovies.model.loader.LoadDataFromAPI
import kotlinx.coroutines.*

class TreadingService :
    Service(), CoroutineScope by MainScope() {
    private val binder: IBinder = ServiceBinder()

    override fun onBind(intent: Intent): IBinder {
        return binder
    }

    private fun loadTreadingService(): MutableList<MoviesTrendingData> {
        var result: MutableList<MoviesTrendingData> = mutableListOf()
        launch(Dispatchers.Default) {
            result = getTreadingMovies()
        }
        return result
    }

    private fun getTreadingMovies(): MutableList<MoviesTrendingData> {
        val trendingDTO: TrendingDTO? = LoadDataFromAPI.loadTrending()
        val listTrendingMovies: MutableList<MoviesTrendingData> = mutableListOf()

        if (trendingDTO != null) {
            trendingDTO.results.forEach {
                listTrendingMovies.add(
                    MoviesTrendingData(
                        it.id,
                        it.title,
                        it.poster_path
                    )
                )
            }
        } else {
            listTrendingMovies.add(MoviesTrendingData(1, "Фильм1"))
        }

        return listTrendingMovies
    }

    internal inner class ServiceBinder : Binder() {
        val service: TreadingService
            get() = this@TreadingService
        val getTreadingMovies: MutableList<MoviesTrendingData>
            get() = service.loadTreadingService()
    }
}