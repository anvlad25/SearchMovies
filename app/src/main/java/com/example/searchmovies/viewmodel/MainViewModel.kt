package com.example.searchmovies.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.searchmovies.model.gson_model.trending.MoviesTrendingData
import com.example.searchmovies.model.gson_model.trending.TrendingDTO
import com.example.searchmovies.model.loader.MoviesRepo
import java.lang.Thread.sleep


class MainViewModel(private val liveDataToObserve: MutableLiveData<List<MoviesTrendingData>> = MutableLiveData()) :
    ViewModel() {

    fun getData(): LiveData<List<MoviesTrendingData>> {
        getMoviesFromSource()
        return liveDataToObserve
    }

    private fun getMoviesFromSource() {
        Thread {
            sleep(Constants.SLEEP_THREAD)
            liveDataToObserve.postValue(getTreadingMovies())
        }.start()
    }

    private fun getTreadingMovies(): MutableList<MoviesTrendingData> {
        val trendingDTO: TrendingDTO? = MoviesRepo.api.getTrending(Constants.API_KEY).execute().body()
        val listTrendingMovies: MutableList<MoviesTrendingData> = mutableListOf()

        trendingDTO?.let { trend ->
            trend.results.forEach {
                listTrendingMovies.add(
                    MoviesTrendingData(
                        it.id,
                        it.title,
                        it.poster_path
                    )
                )
            }
        }

        return listTrendingMovies
    }
}
