package com.example.searchmovies.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.searchmovies.databinding.FragmentDescriptionMovieBinding
import com.example.searchmovies.model.MoviesData
import com.example.searchmovies.viewmodel.Constants
import java.text.SimpleDateFormat
import java.util.*

class DescriptionMovie : Fragment() {
    private var _binding: FragmentDescriptionMovieBinding? = null
    private val binding get() = _binding!!

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
        arguments?.getParcelable<MoviesData>(Constants.MOVIE_DESC)?.let {
            with(binding) {
                descMovieGroup.text = it.movieGroup
                descMovieName.text = it.movieName
                descMovieRating.text = "Рейтинг: ${it.movieRating}"
                descMovieDateFrom.text = "Дата выхода: ${getDateInFormat(it.movieDateFrom)}"
                descMovieText.text = it.movieDesc
            }
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun getDateInFormat(inDate: Date): String = SimpleDateFormat("dd.MM.yyyy").format(inDate)

}