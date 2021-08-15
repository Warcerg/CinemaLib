package com.example.cinemalib.framework.ui.main


import androidx.lifecycle.*
import com.example.cinemalib.model.AppState
import com.example.cinemalib.model.repository.Repository
import java.lang.Thread.sleep

class MainViewModel(private val repository: Repository) : ViewModel(), LifecycleObserver {
    private val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData()

    fun getLiveData() =liveDataToObserve

    fun getMovieData() = getMovieDatafromServer()

    private fun getMovieDatafromServer() {
        liveDataToObserve.value = AppState.Loading
        Thread {
            sleep(1500)
            liveDataToObserve.postValue(AppState.Success(repository.getMovieDatafromServer()))
        }.start()
    }

}