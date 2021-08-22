package com.example.cinemalib.framework.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cinemalib.R
import com.example.cinemalib.databinding.MovieListRecyclerFragmentBinding
import com.example.cinemalib.framework.ui.main.MainFragment
import com.example.cinemalib.model.entities.Movie
import com.example.cinemalib.model.entities.MovieCard

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
            moviePic.setImageResource(R.drawable.generic_movie_poster)
            root.setOnClickListener { itemClickListener.onItemViewClick(movie) }
        }
    }


}