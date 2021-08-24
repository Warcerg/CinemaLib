package com.example.cinemalib.framework.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.cinemalib.R
import com.example.cinemalib.databinding.MovieDetailsFragmentBinding
import com.example.cinemalib.model.AppState
import com.example.cinemalib.model.entities.Movie
import org.koin.androidx.viewmodel.ext.android.viewModel


class MovieDetailsFragment : Fragment() {
    private var _binding: MovieDetailsFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MovieDetailsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MovieDetailsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val movie = arguments?.getParcelable<Movie>(BUNDLE_EXTRA)
        movie?.let {
            with(binding) {
                viewModel.loadData(it.id)
                viewModel.liveDataToObserve.observe(viewLifecycleOwner, { appState ->
                    when (appState) {
                        is AppState.Error -> {
                            Toast.makeText(context, "error", Toast.LENGTH_LONG).show()
                        }
                        is AppState.SuccessMovieCard -> {
                            movieDetailsMovieTitle.text = appState.movieCardData.title
                            movieDetailsRuntime.text =
                                getString(R.string.movieDetails_runtime)
                                    .plus(appState.movieCardData.runtime.toString())
                            movieDetailsReleaseYear.text = appState.movieCardData.release_date.subSequence(0,4)
                            movieDetailsMovieRating.text = appState.movieCardData.rating.toString()
                            movieDetailsPosterImage.setImageResource(R.drawable.generic_movie_poster)
                            movieDetailsMovieSynopsisText.text =
                                appState.movieCardData.plot_overview
                            movieDetailsMovieReleaseDate.text =
                                getString(R.string.movieDetails_release_date)
                                    .plus(" \n")
                                    .plus((appState.movieCardData.release_date))
                            movieDetailsMovieBudget.text =
                                getString(R.string.movieDetails_budget)
                                    .plus(" \n$")
                                    .plus(appState.movieCardData.budget.toString())
                            movieDetailsMovieRevenue.text =
                                getString(R.string.movieDetails_gross_worldwide)
                                    .plus(" \n$")
                                    .plus(appState.movieCardData.revenue.toString())
                            movieDetailsMovieStatus.text =
                                getString(R.string.movieDetails_status)
                                    .plus(" \n")
                                    .plus(appState.movieCardData.status)
                        }
                    }
                })

            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val BUNDLE_EXTRA = "movie data"

        fun newInstance(bundle: Bundle): MovieDetailsFragment {
            val fragment = MovieDetailsFragment()
            fragment.arguments = bundle
            return fragment
        }
    }
}