package com.example.searchmovies.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import androidx.lifecycle.ViewModelProvider
import com.example.searchmovies.R
import com.example.searchmovies.databinding.FragmentDescriptionMovieBinding
import com.example.searchmovies.model.database.Database
import com.example.searchmovies.model.database.NotesEntity
import com.example.searchmovies.model.gson_model.movie.MoviesData
import com.example.searchmovies.model.gson_model.trending.MoviesTrendingData
import com.example.searchmovies.showSnackbar
import com.example.searchmovies.viewmodel.Constants
import com.example.searchmovies.viewmodel.DescriptionMovieModel
import com.squareup.picasso.Picasso

class DescriptionMovie : Fragment() {
    private var _binding: FragmentDescriptionMovieBinding? = null
    private val binding get() = _binding!!
    private var favoriteButton: ImageButton? = null
    private var idMovie: Int = -1


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDescriptionMovieBinding.inflate(inflater, container, false)
        return binding.root
    }


    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel = ViewModelProvider(this).get(DescriptionMovieModel::class.java)
        viewModel.liveDataToObserve.observe(viewLifecycleOwner, { insertDescMovie(it, view) })
        arguments?.getParcelable<MoviesTrendingData>(Constants.MOVIE_DESC)?.let {
            idMovie = it.id
            viewModel.getMoviesFromSource(it.id)
        }

        binding.editTextDesc.setText(Database.db.notesDao().getDataByIdMovie(idMovie)[0].note)
    }

    override fun onDestroy() {
        super.onDestroy()

        val dataBaseDAO = Database.db.notesDao()
        if (idMovie != -1) {
            if (dataBaseDAO.getDataByIdMovie(idMovie)[0].id >= 0) {
                dataBaseDAO.update(toNotesEntity(idMovie, binding.editTextDesc.text.toString()))
            } else {
                dataBaseDAO.insert(toNotesEntity(idMovie, binding.editTextDesc.text.toString()))
            }
        }
    }

    private fun toNotesEntity(idMovie: Int, note: String): NotesEntity =
        NotesEntity(0, idMovie, note)

    private fun insertDescMovie(moviesData: MoviesData?, view: View) {
        moviesData?.let {
            with(binding) {
                descMovieGroup.text = moviesData.movieGroup
                descMovieName.text = moviesData.movieName
                descMovieRating.text = moviesData.movieRating.toString()
                descMovieDateFrom.text = moviesData.movieDateFrom
                descMovieText.text = moviesData.movieDesc
                favoriteButton.setImageResource(getFavoriteImg(moviesData.favorite))
                insertPic(moviesData.poster_path, descMovieImage)

                setFavoriteOrNot(moviesData, view) //!!!
            }
        }
    }

    private fun insertPic(posterPath: String, descMovieImage: ImageView) {
        Picasso
            .get()
            .load("${Constants.IMAGE_URL}$posterPath")
            .into(descMovieImage)
    }

    private fun setFavoriteOrNot(moviesData: MoviesData, view: View) {
        favoriteButton?.let { button ->
            button.setOnClickListener {
                if (moviesData.favorite) {
                    button.setImageResource(R.drawable.ic_baseline_favorite_border_24)
                    moviesData.favorite = false
                    view.showSnackbar(Constants.REMOVE_FAVORITE)
                } else {
                    button.setImageResource(R.drawable.ic_baseline_favorite_24)
                    moviesData.favorite = true
                    view.showSnackbar(Constants.ADD_FAVORITE)
                }
            }
        }
    }

    private fun getFavoriteImg(favorite: Boolean): Int =
        if (favorite) {
            R.drawable.ic_baseline_favorite_24
        } else {
            R.drawable.ic_baseline_favorite_border_24
        }

}