package com.example.cinemalib.framework.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cinemalib.R
import com.example.cinemalib.model.entities.MovieCard

class MovieListAdapter(private val movieCardData: List<MovieCard>) : RecyclerView.Adapter<MovieListAdapter.MyViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.movie_card, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.titleTextView?.text = movieCardData[position].movie.title
        holder.directorTextView?.text = movieCardData[position].movie.director
        holder.releaseDateTextView?.text = movieCardData[position].movie.year
        holder.ratingTextView?.text = movieCardData[position].rating.toString()
        holder.movieImageView?.setImageResource(R.drawable.generic_movie_poster)
    }

    override fun getItemCount() = movieCardData.size

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var titleTextView : TextView? = null
        var directorTextView : TextView? = null
        var releaseDateTextView : TextView? = null
        var ratingTextView : TextView? = null

        var movieImageView : ImageView? = null


        init {
            titleTextView = itemView.findViewById(R.id.movieTitleText)
            directorTextView = itemView.findViewById(R.id.MovieDirectorText)
            releaseDateTextView = itemView.findViewById(R.id.MovieReleaseDateText)
            ratingTextView = itemView.findViewById(R.id.MovieRatingNumberText)
            movieImageView = itemView.findViewById(R.id.MoviePic)
        }
    }


}