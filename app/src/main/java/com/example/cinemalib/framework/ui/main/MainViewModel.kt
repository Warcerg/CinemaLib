package com.example.cinemalib.framework.ui.main


import androidx.lifecycle.*
import com.example.cinemalib.model.AppState
import com.example.cinemalib.model.repository.Repository
import java.lang.Thread.sleep

class MainViewModel(private val repository: Repository) : ViewModel(), LifecycleObserver {
    private val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData()
    private val SLEEPTHREADVALUE: Long = 750

    fun getLiveData() = liveDataToObserve

    fun getMovieData() = getMovieDataFromLocalSource()

    private fun getMovieDataFromLocalSource() {
        Thread {
            sleep(SLEEPTHREADVALUE)
            liveDataToObserve.postValue(AppState.Success(repository.getMovieFromLocalStorage()))
        }.start()
    }

}