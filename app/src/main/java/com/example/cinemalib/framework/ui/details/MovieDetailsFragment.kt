package com.example.cinemalib.framework.ui.details

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.cinemalib.R
import com.example.cinemalib.databinding.MovieDetailsFragmentBinding
import com.example.cinemalib.framework.services.MovieDetailsService
import com.example.cinemalib.model.entities.Movie
import com.example.cinemalib.model.entities.MovieCard
import org.koin.androidx.viewmodel.ext.android.viewModel


class MovieDetailsFragment : Fragment() {
    private var _binding: MovieDetailsFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MovieDetailsViewModel by viewModel()

    private val movieCardReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent) {
            val bundleReceived = intent.getBundleExtra(RECEIVER_BUNDLE_EXTRA)
            val movieCardReceived: MovieCard? =
                bundleReceived!!.getParcelable<MovieCard>(BUNDLE_EXTRA)
            with(binding) {
                movieCardReceived?.let {
                    movieDetailsMovieTitle.text = movieCardReceived.title
                    movieDetailsRuntime.text =
                        getString(R.string.movieDetails_runtime)
                            .plus(movieCardReceived.runtime.toString())
                    movieDetailsReleaseYear.text = movieCardReceived.release_date.subSequence(0, 4)
                    movieDetailsMovieRating.text = movieCardReceived.rating.toString()
                    movieDetailsPosterImage.setImageResource(R.drawable.generic_movie_poster)
                    movieDetailsMovieSynopsisText.text =
                        movieCardReceived.plot_overview
                    movieDetailsMovieReleaseDate.text =
                        getString(R.string.movieDetails_release_date)
                            .plus(" \n")
                            .plus((movieCardReceived.release_date))
                    movieDetailsMovieBudget.text =
                        getString(R.string.movieDetails_budget)
                            .plus(" \n$")
                            .plus(movieCardReceived.budget.toString())
                    movieDetailsMovieRevenue.text =
                        getString(R.string.movieDetails_gross_worldwide)
                            .plus(" \n$")
                            .plus(movieCardReceived.revenue.toString())
                    movieDetailsMovieStatus.text =
                        getString(R.string.movieDetails_status)
                            .plus(" \n")
                            .plus(movieCardReceived.status)
                }
            }

        }
    }


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
        movie?.let { MovieDetailsService.start(requireContext(), it.id) }
    }

    override fun onStart() {
        super.onStart()
        activity?.registerReceiver(
            movieCardReceiver,
            IntentFilter(MovieDetailsService.INTENT_ACTION_KEY)
        )
    }

    override fun onStop() {
        super.onStop()
        activity?.unregisterReceiver(movieCardReceiver)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val BUNDLE_EXTRA = "movie data"
        const val RECEIVER_BUNDLE_EXTRA = "movie card data"

        fun newInstance(bundle: Bundle): MovieDetailsFragment {
            val fragment = MovieDetailsFragment()
            fragment.arguments = bundle
            return fragment
        }
    }
}