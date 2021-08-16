package com.example.cinemalib.framework.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cinemalib.databinding.MainFragmentBinding
import com.example.cinemalib.framework.ui.adapters.MovieListAdapter
import com.example.cinemalib.model.AppState
import com.example.cinemalib.model.entities.MovieCard
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : Fragment() {
    private val viewModel: MainViewModel by viewModel()

    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!

    private var adapter1: MovieListAdapter? = null
    private var adapter2: MovieListAdapter? = null



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            recyclerViewMovies.adapter = adapter1
            recyclerViewMovies.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL, false)
            recyclerViewMovies2.adapter = adapter2
            recyclerViewMovies2.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL, false)
            viewModel.getLiveData().observe(viewLifecycleOwner, { renderData(it) })
            viewModel.getMovieData()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun renderData(appState: AppState) = with(binding) {
        when (appState) {
            is AppState.Success -> {
                adapter1 = MovieListAdapter(object :  onItemClickListener {
                    override fun onItemViewClick(movieCard: MovieCard) {
                        Toast.makeText(context, "Movie details fragment", Toast.LENGTH_SHORT ).show()
                    }
                }
                ).apply {
                    setMovieCard(appState.movieData)
                }
                recyclerViewMovies.adapter = adapter1

                adapter2 = MovieListAdapter(object :  onItemClickListener {
                    override fun onItemViewClick(movieCard: MovieCard) {
                        Toast.makeText(context, "Movie details fragment", Toast.LENGTH_SHORT ).show()
                    }
                }
                ).apply {
                    setMovieCard(appState.movieData)
                }
                recyclerViewMovies2.adapter = adapter2
            }
            is AppState.Loading -> {

            }
            is AppState.Error -> {
            }
        }
    }


    companion object {
        fun newInstance() = MainFragment()
    }

    interface onItemClickListener {
        fun onItemViewClick(movieCard: MovieCard)
    }
}
