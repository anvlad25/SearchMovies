package com.example.searchmovies.viewmodel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.searchmovies.model.gson_model.movie.MovieDTO
import com.example.searchmovies.model.gson_model.movie.MoviesData
import com.example.searchmovies.model.loader.MoviesRepo

class DescriptionMovieModel : ViewModel() {

    val liveDataToObserve: MutableLiveData<MoviesData> = MutableLiveData()

    fun getMoviesFromSource(id: Int) {
        Thread {
            liveDataToObserve.postValue(getDataMovies(id))
        }.start()
    }

    private fun getDataMovies(id: Int): MoviesData {
        val movieDTO: MovieDTO? = MoviesRepo.api.getMovie(id, Constants.API_KEY).execute().body()
        var genres: String = ""

        return if (movieDTO != null) {

            movieDTO.genres.forEach {
                genres += "${it.name}, "
            }

            MoviesData(
                movieDTO.id,
                movieDTO.title,
                movieDTO.overview,
                movieDTO.release_date,
                movieDTO.vote_average,
                genres.substring(0, genres.length - 2),
                movieDTO.poster_path
            )
        } else {
            MoviesData()
        }
    }

}