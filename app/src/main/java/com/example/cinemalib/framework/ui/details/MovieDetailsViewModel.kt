package com.example.cinemalib.framework.ui.details

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cinemalib.model.AppState
import com.example.cinemalib.model.entities.MovieCard
import com.example.cinemalib.model.repository.Repository


class MovieDetailsViewModel(private val repository: Repository) : ViewModel(), LifecycleObserver {
    val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData()

    fun loadData(movie_id: Int) {
        Thread {
            val data = repository.getMovieCardFromServer(movie_id)
            if (repository.isEntityExists(movie_id)) {
                data.note = repository.getNoteEntity(movie_id)
            }
            repository.saveEntity(data)
            liveDataToObserve.postValue(
                AppState.SuccessMovieCard(data)
            )
        }.start()
    }

    fun updateEntity(movCard: MovieCard) {
        Thread {
            repository.saveEntity(movCard)
        }.start()

    }

}