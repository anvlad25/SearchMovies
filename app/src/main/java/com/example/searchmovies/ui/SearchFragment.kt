package com.example.searchmovies.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.ViewModelProvider
import com.example.searchmovies.databinding.FragmentSearchBinding
import com.example.searchmovies.model.gson_model.searchmovie.SearchData
import com.example.searchmovies.viewmodel.Constants
import com.example.searchmovies.viewmodel.SearchMovieModel
import com.squareup.picasso.Picasso

class SearchFragment : Fragment() {
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel = ViewModelProvider(this).get(SearchMovieModel::class.java)
        binding.buttonSearch.setOnClickListener {
            viewModel.getSearchMoviesFromSource(binding.querySearch.text.toString())
        }
        viewModel.liveDataToObserve.observe(viewLifecycleOwner, { insertSearchMovie(it) })
    }


    private fun insertSearchMovie(searchData: SearchData?) {
        searchData?.let {
            with(binding) {
                searchMovieName.text = searchData.title
                searchMovieText.text = searchData.overview
                searchData.poster_path?.let { insertPic(it, searchMovieImage) }
            }
        }
    }

    private fun insertPic(posterPath: String, descMovieImage: ImageView) {
        Picasso
            .get()
            .load("${Constants.IMAGE_URL}$posterPath")
            .into(descMovieImage)
    }
}