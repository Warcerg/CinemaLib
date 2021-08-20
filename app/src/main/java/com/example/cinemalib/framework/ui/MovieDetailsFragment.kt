package com.example.cinemalib.framework.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.cinemalib.R
import com.example.cinemalib.databinding.MovieDetailsFragmentBinding
import com.example.cinemalib.model.entities.MovieCard
import com.example.cinemalib.formatToString


class MovieDetailsFragment : Fragment() {
    private var _binding: MovieDetailsFragmentBinding? = null
    private val binding get() = _binding!!
    private val ratingEnd = "/10"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MovieDetailsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("ResourceType")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getParcelable<MovieCard>(BUNDLE_EXTRA)?.let {
            with(binding) {
                movieDetailsMovieTitle.text = it.movie.title
                movieDetailsMovieDirector.text = it.movie.director
                movieDetailsReleaseYear.text = it.movie.year
                movieDetailsMovieBudget.text =
                    getString(R.string.movieDetails_budget).plus(" \n").plus(it.movie.budget)
                movieDetailsMovieGenre.text =
                    getString(R.string.movieDetails_genre).plus(" \n").plus(it.genre)
                movieDetailsMovieReleaseDate.text = getString(R.string.movieDetails_release_date)
                    .plus(" \n")
                    .plus((it.movie.releaseDate.formatToString()))
                movieDetailsMovieSynopsisText.text = it.movie.synopsis
                movieDetailsMovieGrossWorldwide.text =
                    getString(R.string.movieDetails_gross_worldwide).plus(" \n").plus(it.gross)
                movieDetailsMovieTriviaText.text = it.trivia
                movieDetailsMovieRating.text = it.rating.toString().plus(ratingEnd)
                movieDetailsPosterImage.setImageResource(R.drawable.generic_movie_poster)
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