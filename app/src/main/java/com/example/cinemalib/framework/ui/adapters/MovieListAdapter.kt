package com.example.cinemalib.framework.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cinemalib.R
import com.example.cinemalib.databinding.MovieListRecyclerFragmentBinding
import com.example.cinemalib.framework.ui.main.MainFragment
import com.example.cinemalib.model.entities.MovieCard

class MovieListAdapter(private var itemClickListener: MainFragment.OnItemClickListener) :
    RecyclerView.Adapter<MovieListAdapter.MainViewHolder>() {
    private var movieCardData: List<MovieCard> = listOf()
    private lateinit var binding: MovieListRecyclerFragmentBinding

    @SuppressLint("NotifyDataSetChanged")
    fun setMovieCard(data: List<MovieCard>) {
        movieCardData = data
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
        holder.bind(movieCardData[position])
    }

    override fun getItemCount() = movieCardData.size

    inner class MainViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(movieCard: MovieCard) = with(binding) {
            movieTitleText.text = movieCard.movie.title
            movieDirectorText.text = movieCard.movie.director
            movieReleaseDateText.text = movieCard.movie.year
            movieRatingNumberText.text = movieCard.rating.toString()
            moviePic.setImageResource(R.drawable.generic_movie_poster)
            root.setOnClickListener { itemClickListener.onItemViewClick(movieCard) }
        }
    }


}