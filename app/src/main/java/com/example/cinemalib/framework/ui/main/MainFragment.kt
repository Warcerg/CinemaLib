package com.example.cinemalib.framework.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.cinemalib.R
import com.example.cinemalib.databinding.MainFragmentBinding
import com.example.cinemalib.model.AppState
import com.example.cinemalib.model.entities.MovieCard
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : Fragment() {
    private val viewModel: MainViewModel by viewModel()
    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
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
