package com.example.cinemalib.framework.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import coil.size.Scale
import com.example.cinemalib.R
import com.example.cinemalib.databinding.HistoryRecyclerFragmentBinding
import com.example.cinemalib.model.entities.MovieCard
import com.example.cinemalib.model.received_data.ApiUtils

class HistoryListAdapter: RecyclerView.Adapter<HistoryListAdapter.RecyclerItemViewHolder>() {
    private var data: List<MovieCard> = arrayListOf()

    fun setData( data: List<MovieCard>){
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerItemViewHolder =
            RecyclerItemViewHolder(
                    HistoryRecyclerFragmentBinding.inflate(
                            LayoutInflater.from(parent.context), parent, false
                    )
            )


    override fun onBindViewHolder(holder: RecyclerItemViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class RecyclerItemViewHolder( private val binding: HistoryRecyclerFragmentBinding)
        :RecyclerView.ViewHolder(binding.root){

        fun bind(data: MovieCard) = with(binding){
                if(layoutPosition != RecyclerView.NO_POSITION) {
                    historyMoviePoster.load("${ApiUtils.posterUrl}${data.poster}") {
                        scale(Scale.FIT)
                        placeholder(R.drawable.generic_movie_poster)
                    }
                    historyTitle.text = data.title
                    historyReleaseDate.text = data.release_date
                    historyRuntime.text = data.runtime.toString()
                    historyRating.text = data.rating.toString()
/*                    // добавить ссылку на фрагмент details
                    root.setOnClickListener {  }*/
                }
            }
    }
}