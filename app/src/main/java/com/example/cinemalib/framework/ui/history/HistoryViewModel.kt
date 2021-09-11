package com.example.cinemalib.framework.ui.history

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cinemalib.model.AppState
import com.example.cinemalib.model.repository.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class HistoryViewModel(private val repository: Repository) : ViewModel(),
    CoroutineScope by MainScope() {
    val historyLiveData: MutableLiveData<AppState> = MutableLiveData()

    fun getAllHistory() {
        launch(Dispatchers.IO) {
            historyLiveData.postValue(AppState.SuccessMovieCardHistory(repository.getAllHistory()))
        }
    }

}