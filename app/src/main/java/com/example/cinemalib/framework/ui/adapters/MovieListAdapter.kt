package com.example.cinemalib.framework.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.Scale
import com.example.cinemalib.R
import com.example.cinemalib.databinding.MovieListRecyclerFragmentBinding
import com.example.cinemalib.framework.ui.main.MainFragment
import com.example.cinemalib.model.entities.Movie
import com.example.cinemalib.model.received_data.ApiUtils


class MovieListAdapter(private var itemClickListener: MainFragment.OnItemClickListener) :
    RecyclerView.Adapter<MovieListAdapter.MainViewHolder>() {
    private var movieListData: List<Movie> = listOf()
    private lateinit var binding: MovieListRecyclerFragmentBinding

    @SuppressLint("NotifyDataSetChanged")
    fun setMovieList(data: List<Movie>) {
        movieListData = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        binding = MovieListRecyclerFragmentBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MainViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(movieListData[position])
    }

    override fun getItemCount() = movieListData.size

    inner class MainViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(movie: Movie) = with(binding) {
            movieTitleText.text = movie.title
            movieReleaseDateText.text = movie.releaseDate
            movieRatingNumberText.text = movie.rating.toString()
            moviePic.load("${ApiUtils.posterUrl}${movie.poster}") {
                scale(Scale.FIT)
                placeholder(R.drawable.generic_movie_poster)
            }
            root.setOnClickListener { itemClickListener.onItemViewClick(movie) }
        }
    }


}