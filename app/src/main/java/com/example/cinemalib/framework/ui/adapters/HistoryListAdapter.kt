package com.example.cinemalib.framework.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import coil.size.Scale
import com.example.cinemalib.R
import com.example.cinemalib.databinding.HistoryRecyclerFragmentBinding
import com.example.cinemalib.databinding.MovieListRecyclerFragmentBinding
import com.example.cinemalib.framework.ui.history.HistoryFragment
import com.example.cinemalib.model.entities.MovieCard
import com.example.cinemalib.model.received_data.ApiUtils

class HistoryListAdapter(private var itemClickListener: HistoryFragment.OnItemClickListener) :
    RecyclerView.Adapter<HistoryListAdapter.HistoryViewHolder>() {
    private var data: List<MovieCard> = arrayListOf()
    private lateinit var binding: HistoryRecyclerFragmentBinding

    fun setData(data: List<MovieCard>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        binding = HistoryRecyclerFragmentBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return HistoryViewHolder(binding.root)
    }


    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class HistoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(data: MovieCard) = with(binding) {
            if (layoutPosition != RecyclerView.NO_POSITION) {
                historyMoviePoster.load("${ApiUtils.posterUrl}${data.poster}") {
                    scale(Scale.FIT)
                    placeholder(R.drawable.generic_movie_poster)
                }
                historyTitle.text = data.title
                historyReleaseDate.text = data.release_date
                historyRuntime.text = data.runtime.toString()
                historyRating.text = data.rating.toString()
                root.setOnClickListener { itemClickListener.onItemViewClick(data) }
            }
        }
    }
}