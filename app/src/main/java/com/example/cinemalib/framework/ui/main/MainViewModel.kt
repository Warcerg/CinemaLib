package com.example.cinemalib.framework.ui.main


import androidx.lifecycle.*
import com.example.cinemalib.model.AppState
import com.example.cinemalib.model.repository.Repository
import java.lang.Thread.sleep

class MainViewModel(private val repository: Repository) : ViewModel(), LifecycleObserver {
    private val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData()
    private val SLEEPVALUE: Long = 500

    fun getLiveData() = liveDataToObserve

    fun getMovieData(queryMovieList: String, queryMovieList2: String) =
        getMovieDataFromServer(queryMovieList, queryMovieList2)

    private fun getMovieDataFromServer(queryMovieList: String, queryMovieList2: String) {
        Thread {
            sleep(SLEEPVALUE)
            val mvList = mutableListOf(
                repository.getMovieDataFromServer(queryMovieList),
                repository.getMovieDataFromServer(queryMovieList2)
            )
            liveDataToObserve.postValue(AppState.SuccessMovieLists(mvList))
        }.start()
    }

}