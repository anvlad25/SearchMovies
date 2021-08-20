package com.example.searchmovies.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import com.example.searchmovies.R
import com.example.searchmovies.databinding.FragmentDescriptionMovieBinding
import com.example.searchmovies.formatStr
import com.example.searchmovies.model.MoviesData
import com.example.searchmovies.showSnackbar
import com.example.searchmovies.viewmodel.Constants

class DescriptionMovie : Fragment() {
    private var _binding: FragmentDescriptionMovieBinding? = null
    private val binding get() = _binding!!
    private var favoriteButton: ImageButton? = null

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
        favoriteButton = view.findViewById(R.id.favoriteButton)

        arguments?.getParcelable<MoviesData>(Constants.MOVIE_DESC)?.let {
            with(binding) {
                descMovieGroup.text = it.movieGroup
                descMovieName.text = it.movieName
                descMovieRating.text = "Рейтинг: ${it.movieRating}"
                descMovieDateFrom.text = "Дата выхода: ${it.movieDateFrom.formatStr()}"
                descMovieText.text = it.movieDesc
                favoriteButton.setImageResource(getFavoriteImg(it.favorite))

                setFavoriteOrNot(it, view)
            }
        }

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