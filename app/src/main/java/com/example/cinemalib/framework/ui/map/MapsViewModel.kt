package com.example.cinemalib.framework.ui.map

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cinemalib.model.AppState
import com.example.cinemalib.model.repository.Repository

class MapsViewModel(private val repository: Repository): ViewModel(), LifecycleObserver {
    val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData()

    fun loadData(person_id: Int) {
        Thread {
            val data = repository.getPlaceOfBirth(person_id)
            liveDataToObserve.postValue(
                AppState.SuccessPlaceOfBirth(data)
            )
        }.start()
    }
}