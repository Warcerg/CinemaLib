package com.example.cinemalib.di

import com.example.cinemalib.framework.ui.details.MovieDetailsViewModel
import com.example.cinemalib.framework.ui.history.HistoryViewModel
import com.example.cinemalib.framework.ui.main.MainViewModel
import com.example.cinemalib.framework.ui.map.MapsViewModel
import com.example.cinemalib.model.repository.Repository
import com.example.cinemalib.model.repository.RepositoryImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<Repository> { RepositoryImpl() }

    //View models
    viewModel { MainViewModel(get()) }
    viewModel { MovieDetailsViewModel(get()) }
    viewModel { HistoryViewModel(get()) }
    viewModel { MapsViewModel(get()) }

}