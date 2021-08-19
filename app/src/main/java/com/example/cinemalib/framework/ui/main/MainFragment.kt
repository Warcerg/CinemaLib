package com.example.cinemalib.framework.ui.main

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cinemalib.R
import com.example.cinemalib.databinding.MainFragmentBinding
import com.example.cinemalib.framework.ui.MovieDetailsFragment
import com.example.cinemalib.framework.ui.adapters.MovieListAdapter
import com.example.cinemalib.model.AppState
import com.example.cinemalib.model.entities.MovieCard
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : Fragment() {
    private val viewModel: MainViewModel by viewModel()

    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!

    private var adapterNewReleasesList: MovieListAdapter? = null
    private var adapterTopMoviesList: MovieListAdapter? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            recyclerViewMovies.adapter = adapterNewReleasesList
            recyclerViewMovies.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            recyclerViewMovies2.adapter = adapterTopMoviesList
            recyclerViewMovies2.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
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
                adapterNewReleasesList = MovieListAdapter(object : OnItemClickListener {
                    override fun onItemViewClick(movieCard: MovieCard) {
                        val managerFR = activity?.supportFragmentManager
                        managerFR?.let { manager ->
                            val bundle = Bundle().apply {
                                putParcelable(MovieDetailsFragment.BUNDLE_EXTRA, movieCard)
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
                    setMovieCard(appState.movieData)
                }
                recyclerViewMovies.adapter = adapterNewReleasesList

                adapterTopMoviesList = MovieListAdapter(object : OnItemClickListener {
                    override fun onItemViewClick(movieCard: MovieCard) {
                        val managerFR = activity?.supportFragmentManager
                        managerFR?.let { manager ->
                            val bundle = Bundle().apply {
                                putParcelable(MovieDetailsFragment.BUNDLE_EXTRA, movieCard)
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
                    setMovieCard(appState.movieData.filter { it.rating >= 9 })
                }
                recyclerViewMovies2.adapter = adapterTopMoviesList
            }
            is AppState.Error -> {
                Snackbar
                    .make(textView2, getString(R.string.error), Snackbar.LENGTH_INDEFINITE)
                    .setAction(getString(R.string.reload)) { viewModel.getMovieData() }
                    .show()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.top_menu, menu)
        val search = menu.findItem(R.id.action_search)
        val searchText = search.actionView as SearchView
        searchText.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                Toast.makeText(activity, query, Toast.LENGTH_SHORT).show()
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return true
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_settings -> {
                Toast.makeText(context, R.string.settings_frag, Toast.LENGTH_LONG).show()
                return true
            }
            R.id.action_about -> {
                Toast.makeText(context, R.string.about_app_frag, Toast.LENGTH_LONG).show()
                return true
            }
        }
        return false
    }

    companion object {
        fun newInstance() = MainFragment()
    }

    interface OnItemClickListener {
        fun onItemViewClick(movieCard: MovieCard)
    }
}
