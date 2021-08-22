package com.example.cinemalib.framework.ui.main


import androidx.lifecycle.*
import com.example.cinemalib.model.AppState
import com.example.cinemalib.model.repository.Repository
import java.lang.Thread.sleep

class MainViewModel(private val repository: Repository) : ViewModel(), LifecycleObserver {
    private val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData()
    private val SLEEPVALUE: Long = 500

    fun getLiveData() = liveDataToObserve

    fun getMovieData(queryMovieList: String) = getMovieDataFromServer(queryMovieList)

    private fun getMovieDataFromServer(queryMovieList: String) {
        Thread {
            sleep(SLEEPVALUE)
            liveDataToObserve.postValue(AppState.SuccessMovieList(repository.getMovieDataFromServer(queryMovieList)))
        }.start()
    }

}