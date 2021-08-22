package com.example.cinemalib.framework.ui.details

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cinemalib.model.AppState
import com.example.cinemalib.model.repository.Repository

class MovieDetailsViewModel(private val repository: Repository) : ViewModel(), LifecycleObserver {
    val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData()

    fun LoadData(movie_id: Int) {
        Thread {
            liveDataToObserve.postValue(AppState.SuccessMovieCard(repository.getMovieCardFromServer(movie_id)))
        }.start()
    }
}