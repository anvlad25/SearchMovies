package com.example.searchmovies.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.searchmovies.databinding.ItemMovieBinding
import com.example.searchmovies.model.MoviesData
import com.example.searchmovies.ui.MainFragment


class MainFragmentAdapter(private var itemClickListener: MainFragment.OnItemViewClickListener) :
    RecyclerView.Adapter<MainFragmentAdapter.MainViewHolder>() {
    private var moviesData: List<MoviesData> = listOf()
    private lateinit var binding: ItemMovieBinding

    fun setMovie(data: List<MoviesData>) {
        moviesData = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        binding = ItemMovieBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return MainViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(moviesData[position])
    }

    override fun getItemCount() = moviesData.size

    inner class MainViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(movie: MoviesData) = with(binding) {
            movieText.text = movie.movieName
            root.setOnClickListener { itemClickListener.onItemViewClick(movie) }
        }
    }
}