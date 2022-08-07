package com.example.cinemalib.framework.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.Scale
import com.example.cinemalib.R
import com.example.cinemalib.databinding.CastRecyclerFragmentBinding
import com.example.cinemalib.framework.ui.details.MovieDetailsFragment
import com.example.cinemalib.model.entities.CastEntity
import com.example.cinemalib.model.received_data.ApiUtils

class CastListAdapter(private var itemClickListener: MovieDetailsFragment.OnItemClickListener) :
    RecyclerView.Adapter<CastListAdapter.CastViewHolder>() {
    private var data: List<CastEntity> = arrayListOf()
    private lateinit var binding: CastRecyclerFragmentBinding

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: List<CastEntity>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CastViewHolder {
        binding = CastRecyclerFragmentBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false)
        return CastViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: CastListAdapter.CastViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class CastViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(data: CastEntity) = with(binding) {
            if (layoutPosition != RecyclerView.NO_POSITION) {
                personPhoto.load("${ApiUtils.posterUrl}${data.profilePath}") {
                    scale(Scale.FIT)
                    placeholder(R.drawable.generic_movie_poster)
                }
                castName.text = data.name
                characterName.text = data.character
                root.setOnClickListener { itemClickListener.onItemViewClick(data.id) }
            }
        }
    }

}