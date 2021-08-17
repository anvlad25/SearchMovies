package com.example.searchmovies.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

import androidx.lifecycle.ViewModel
import com.example.searchmovies.model.MoviesData
import java.lang.Thread.sleep


class MainViewModel(private val liveDataToObserve: MutableLiveData<List<MoviesData>> = MutableLiveData()) :
    ViewModel() {

    fun getData(): LiveData<List<MoviesData>> {
        getMoviesFromSource()
        return liveDataToObserve
    }

    private fun getMoviesFromSource() {
        Thread {
            sleep(1000)
            liveDataToObserve.postValue(getDataMovies())
        }.start()
    }

    private fun getDataMovies() =
        listOf(
            MoviesData("Фильм1", "Описание1"),
            MoviesData("Фильм2", "Описание2"),
            MoviesData("Фильм3", "Описание3"),
            MoviesData("Фильм4", "Описание4")
        )
}
