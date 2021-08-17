package com.example.searchmovies.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.searchmovies.R
import com.example.searchmovies.databinding.FragmentMoviesBinding
import com.example.searchmovies.model.MoviesData
import com.example.searchmovies.ui.adapter.MainFragmentAdapter
import com.example.searchmovies.viewmodel.Constants
import com.example.searchmovies.viewmodel.MainViewModel

class MainFragment : Fragment() {
    private var adapter: MainFragmentAdapter? = null

    private var _binding: FragmentMoviesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.getData().observe(viewLifecycleOwner, { insertMovie(it) })
    }

    private fun insertMovie(moniesList: List<MoviesData>) = with(binding) {
        adapter = MainFragmentAdapter(object : OnItemViewClickListener {
            override fun onItemViewClick(movie: MoviesData) {
                val manager = activity?.supportFragmentManager
                manager?.let { manager ->
                    val bundle = Bundle().apply {
                        putParcelable(Constants.MOVIE_DESC, movie)
                    }
                    manager.beginTransaction()
                        .replace(R.id.container, newInstance(bundle))
                        .addToBackStack("")
                        .commitAllowingStateLoss()
                }
            }
        }).apply {
            setMovie(moniesList)
        }
        recyclerViewMovies.adapter = adapter
    }

    interface OnItemViewClickListener {
        fun onItemViewClick(movie: MoviesData)
    }

    companion object {
        fun newInstance() = MainFragment()

        fun newInstance(bundle: Bundle): DescriptionMovie {
            val fragment = DescriptionMovie()
            fragment.arguments = bundle
            return fragment
        }
    }
}
