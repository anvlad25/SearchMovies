package com.example.searchmovies.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.searchmovies.model.gson_model.trending.MoviesTrendingData
import com.example.searchmovies.model.gson_model.trending.TrendingDTO
import com.example.searchmovies.model.loader.LoadDataFromAPI
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
}
