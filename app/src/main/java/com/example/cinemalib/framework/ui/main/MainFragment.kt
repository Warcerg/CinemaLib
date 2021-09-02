package com.example.cinemalib.framework.ui.main

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cinemalib.R
import com.example.cinemalib.databinding.MainFragmentBinding
import com.example.cinemalib.framework.ui.details.MovieDetailsFragment
import com.example.cinemalib.framework.ui.adapters.MovieListAdapter
import com.example.cinemalib.framework.ui.settings.SettingsFragment
import com.example.cinemalib.model.AppState
import com.example.cinemalib.model.entities.Movie
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : Fragment() {
    private val viewModel: MainViewModel by viewModel()

    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!

    private var adapterNowPlayingList: MovieListAdapter? = null
    private var adapterTopMoviesList: MovieListAdapter? = null
    private val NOWPLAYING = "now_playing"
    private val TOPRATED = "top_rated"


    private var isAdultFilter: Boolean = false


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadData()
        with(binding) {
            recyclerViewMovies.adapter = adapterNowPlayingList
            recyclerViewMovies.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            recyclerViewMovies2.adapter = adapterTopMoviesList
            recyclerViewMovies2.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            viewModel.getLiveData().observe(viewLifecycleOwner, { renderData(it) })
            viewModel.getMovieData(NOWPLAYING, TOPRATED)
        }
    }

    private fun loadData() {
        activity?.let {
            isAdultFilter =
                it.getPreferences(Context.MODE_PRIVATE).getBoolean(SettingsFragment.ADULTFILTER, false)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun renderData(appState: AppState) = with(binding) {
        when (appState) {
            is AppState.SuccessMovieLists -> {
                adapterNowPlayingList = MovieListAdapter(object : OnItemClickListener {
                    override fun onItemViewClick(movie: Movie) {
                        val managerFR = activity?.supportFragmentManager
                        managerFR?.let { manager ->
                            val bundle = Bundle().apply {
                                putParcelable(MovieDetailsFragment.BUNDLE_EXTRA, movie)
                            }
                            manager.beginTransaction()
                                .add(R.id.container, MovieDetailsFragment.newInstance(bundle))
                                .addToBackStack("")
                                .remove(this@MainFragment)
                                .commitAllowingStateLoss()
                        }
                    }
                }
                ).apply {
                    if (isAdultFilter) {
                        setMovieList(appState.movieData[0].filter { !it.adult })
                    } else {
                        setMovieList(appState.movieData[0])
                    }
                }
                recyclerViewMovies.adapter = adapterNowPlayingList

                adapterTopMoviesList = MovieListAdapter(object : OnItemClickListener {
                    override fun onItemViewClick(movie: Movie) {
                        val managerFR = activity?.supportFragmentManager
                        managerFR?.let { manager ->
                            val bundle = Bundle().apply {
                                putParcelable(MovieDetailsFragment.BUNDLE_EXTRA, movie)
                            }
                            manager.beginTransaction()
                                .add(R.id.container, MovieDetailsFragment.newInstance(bundle))
                                .addToBackStack("")
                                .remove(this@MainFragment)
                                .commitAllowingStateLoss()
                        }
                    }
                }
                ).apply {
                    if (isAdultFilter) {
                        setMovieList(appState.movieData[1].filter { !it.adult })
                    } else {
                        setMovieList(appState.movieData[1])
                    }
                }
                recyclerViewMovies2.adapter = adapterTopMoviesList
            }
            is AppState.Error -> {
                Snackbar
                    .make(textView2, getString(R.string.error), Snackbar.LENGTH_INDEFINITE)
                    .setAction(getString(R.string.reload)) {
                        viewModel.getMovieData(
                            NOWPLAYING,
                            TOPRATED
                        )
                    }
                    .show()
            }
        }
    }

    companion object {
        fun newInstance() = MainFragment()
    }

    interface OnItemClickListener {
        fun onItemViewClick(movieCard: Movie)
    }
}
