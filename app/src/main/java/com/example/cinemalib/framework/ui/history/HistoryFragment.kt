package com.example.cinemalib.framework.ui.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.cinemalib.R
import com.example.cinemalib.databinding.HistoryFragmentBinding
import com.example.cinemalib.framework.ui.adapters.HistoryListAdapter
import com.example.cinemalib.framework.ui.details.MovieDetailsFragment
import com.example.cinemalib.model.AppState
import com.example.cinemalib.model.entities.Movie
import com.example.cinemalib.model.entities.MovieCard
import org.koin.androidx.viewmodel.ext.android.viewModel

class HistoryFragment : Fragment() {
    private var _binding: HistoryFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HistoryViewModel by viewModel()
    private var adapter: HistoryListAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = HistoryFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        super.onViewCreated(view, savedInstanceState)
        historyRecyclerView.adapter = adapter
        viewModel.historyLiveData.observe(viewLifecycleOwner, { renderData(it) })
        viewModel.getAllHistory()
    }

    private fun renderData(appState: AppState) = with(binding) {
        when (appState) {
            is AppState.SuccessMovieCardHistory -> {
                historyRecyclerView.adapter = HistoryListAdapter(object : OnItemClickListener {
                    override fun onItemViewClick(movieCard: MovieCard) {
                        val managerFR = activity?.supportFragmentManager
                        managerFR?.let { manager ->
                            val bundle = Bundle().apply {
                                val movie: Movie = movieCard.toMovie()
                                putParcelable(MovieDetailsFragment.BUNDLE_EXTRA, movie)
                            }
                            manager.beginTransaction()
                                .add(R.id.container, MovieDetailsFragment.newInstance(bundle))
                                .addToBackStack("")
                                .remove(this@HistoryFragment)
                                .commitAllowingStateLoss()
                        }
                    }
                }
                ).apply {
                    setData(appState.movieCardData)
                }

            }
            is AppState.Error -> {
                Toast.makeText(context, "error", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() = HistoryFragment()
    }

    interface OnItemClickListener {
        fun onItemViewClick(movieCard: MovieCard)
    }
}