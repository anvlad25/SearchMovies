package com.example.searchmovies.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

import androidx.lifecycle.ViewModel
import com.example.searchmovies.model.MoviesData
import java.lang.Thread.sleep
import java.util.*


class MainViewModel(private val liveDataToObserve: MutableLiveData<List<MoviesData>> = MutableLiveData()) :
    ViewModel() {

    fun getData(): LiveData<List<MoviesData>> {
        getMoviesFromSource()
        return liveDataToObserve
    }

    private fun getMoviesFromSource() {
        Thread {
            sleep(Constants.SLEEP_THREAD)
            liveDataToObserve.postValue(getDataMovies())
        }.start()
    }

    private fun getDataMovies() =
        listOf(
            MoviesData("Фильм1", "Описание1", Date(), 5, "Комедия"),
            MoviesData("Фильм2", "Описание2", Date(), 4, "Ужасы"),
            MoviesData("Фильм3", "Описание3", Date(), 7, "Боевик"),
            MoviesData("Фильм4", "Описание4", Date(), 9, "Исторический")
        )
}
