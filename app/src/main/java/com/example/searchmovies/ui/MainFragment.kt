package com.example.searchmovies.ui

import android.content.*
import android.os.Bundle
import android.os.IBinder
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.searchmovies.R
import com.example.searchmovies.databinding.FragmentMoviesBinding
import com.example.searchmovies.model.gson_model.trending.MoviesTrendingData
import com.example.searchmovies.model.service.TreadingService.ServiceBinder
import com.example.searchmovies.ui.adapter.MainFragmentAdapter
import com.example.searchmovies.viewmodel.Constants
import com.example.searchmovies.viewmodel.MainViewModel

class MainFragment : Fragment() {
    private var adapter: MainFragmentAdapter? = null

    private var _binding: FragmentMoviesBinding? = null
    private val binding get() = _binding!!

    private var isBound = false
    private var boundService: ServiceBinder? = null

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

    private val boundServiceConnection: ServiceConnection = object : ServiceConnection {

        override fun onServiceConnected(name: ComponentName, service: IBinder) {
            boundService = service as ServiceBinder
            isBound = boundService != null

            insertMovie(service.getTreadingMovies)
        }

        override fun onServiceDisconnected(name: ComponentName) {
            isBound = false
            boundService = null
        }
    }

    /*override fun onStart() {
        super.onStart()
        if (!isBound) {
            val bindServiceIntent = Intent(context, TreadingService::class.java)
            activity?.bindService(
                bindServiceIntent, boundServiceConnection,
                Context.BIND_AUTO_CREATE
            )
        }
    }*/

    /*override fun onStop() {
        if (isBound) {
            activity?.unbindService(boundServiceConnection)
        }
        super.onStop()
    }*/

    private fun insertMovie(moviesList: List<MoviesTrendingData>) = with(binding) {
        adapter = MainFragmentAdapter(object : OnItemViewClickListener {
            override fun onItemViewClick(movie: MoviesTrendingData) {
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
            setMovie(moviesList)
        }
        recyclerViewMovies.adapter = adapter
    }

    interface OnItemViewClickListener {
        fun onItemViewClick(movie: MoviesTrendingData)
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
