package com.example.cinemalib.framework.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cinemalib.databinding.MainFragmentBinding
import com.example.cinemalib.model.AppState
import com.example.cinemalib.model.entities.MovieCard
import com.example.cinemalib.model.entities.getDefaultMovie
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : Fragment() {
    private val viewModel: MainViewModel by viewModel()
    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!

    private val movCard1 = MovieCard(getDefaultMovie(),5 , )
    private val movCard2 = MovieCard(getDefaultMovie(),5 , )
    private val movCard3 = MovieCard(getDefaultMovie(),5 , )



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        initRecyclerView()
        return binding.root
    }

    private fun initRecyclerView() {
        val recyclerView: RecyclerView = _binding!!.recyclerViewMovies
        recyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        var MovieList: List<MovieCard> = listOf(movCard1, movCard2, movCard3, movCard1, movCard1, movCard1, movCard1, movCard1, )
        recyclerView.adapter = MovieListAdapter(MovieList)

        val recyclerView2:RecyclerView = _binding!!.recyclerViewMovies2
        recyclerView2.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        recyclerView2.adapter = MovieListAdapter(MovieList)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val observer = Observer<AppState> {renderData(it)}
        viewLifecycleOwner.lifecycle.addObserver(viewModel)
        viewModel.getLiveData().observe(viewLifecycleOwner, observer)
        viewModel.getMovieData()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun renderData(appState: AppState) = with(binding) {
        when (appState) {
            is AppState.Success -> {
                val movieData = appState.movieData
                setData(movieData)
            }
            is AppState.Loading -> {

            }
            is AppState.Error -> {
            }
        }
    }

    private fun setData(movieData: MovieCard) = with(binding) {

    }

    companion object {
        fun newInstance() = MainFragment()
    }
}
