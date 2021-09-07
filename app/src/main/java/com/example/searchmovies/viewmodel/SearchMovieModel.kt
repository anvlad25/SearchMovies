package com.example.searchmovies.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.searchmovies.model.App
import com.example.searchmovies.model.gson_model.searchmovie.SearchData
import com.example.searchmovies.model.gson_model.searchmovie.SearchMoviesDTO
import com.example.searchmovies.model.loader.MoviesRepo

class SearchMovieModel : ViewModel() {

    val liveDataToObserve: MutableLiveData<SearchData> = MutableLiveData()

    fun getSearchMoviesFromSource(query: String) {
        Thread {
            liveDataToObserve.postValue(getSearchMovies(query))
        }.start()
    }

    private fun getSearchMovies(query: String): SearchData {

        var resultPref = App.appContext.getSharedPreferences("test", Context.MODE_PRIVATE)
            ?.getBoolean(Constants.INCLUDE_ADULT, true)

        if(resultPref == null) {
            resultPref = false
        }
        val searchDTO: SearchMoviesDTO? = MoviesRepo.api.getMovieSearch(Constants.API_KEY, query, resultPref).execute().body()

        return if (searchDTO != null) {
            SearchData(
                searchDTO.results[0].id,
                searchDTO.results[0].title,
                searchDTO.results[0].overview,
                searchDTO.results[0].poster_path
            )
        } else {
            SearchData()
        }
    }
}