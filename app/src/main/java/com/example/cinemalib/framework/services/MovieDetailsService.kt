package com.example.cinemalib.framework.services

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.IBinder
import com.example.cinemalib.framework.ui.details.MovieDetailsFragment
import com.example.cinemalib.model.MovieDetailsLoader
import com.example.cinemalib.model.entities.MovieCard
import kotlinx.coroutines.*

class MovieDetailsService : Service(), CoroutineScope by MainScope() {
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        launch {
            val movieID: Int = intent?.getIntExtra(MOVIE_ID, 0) ?: 0
            val job = async(Dispatchers.IO) {
                MovieDetailsLoader.loadMovieDetails(movieID)
            }
            val dto = job.await()
            val movieCard = MovieCard(
                id = dto?.id ?: 0,
                title = dto?.title ?: "",
                budget = dto?.budget ?: 0,
                plot_overview = dto?.overview ?: "",
                rating = dto?.vote_average ?: 0.0,
                release_date = dto?.release_date ?: "0000",
                revenue = dto?.revenue ?: 0,
                runtime = dto?.runtime ?: 0,
                status = dto?.status ?: ""
            )
            sendMovieDataBroadcast(movieCard)
        }
        return START_NOT_STICKY
    }

    private fun sendMovieDataBroadcast(movieCard: MovieCard) {
        val broadcastIntent = Intent()
        val bundle = Bundle().apply {
            putParcelable(MovieDetailsFragment.BUNDLE_EXTRA, movieCard)
        }
        broadcastIntent.putExtra(MovieDetailsFragment.RECEIVER_BUNDLE_EXTRA, bundle)
        broadcastIntent.action = INTENT_ACTION_KEY
        sendBroadcast(broadcastIntent)
    }

    override fun onBind(intent: Intent?): IBinder? = null

    companion object {
        const val INTENT_ACTION_KEY = "com.example.cinemalib.SERVICE_FINISHED_EVENT"
        const val MOVIE_ID = "MOVIE_ID"

        fun start(context: Context, movieID: Int) {
            val movID = movieID
            val movieDetailsServiceIntent = Intent(context, MovieDetailsService::class.java)
            movieDetailsServiceIntent.putExtra(MOVIE_ID, movID)
            context.startService(movieDetailsServiceIntent)
        }

        fun end(context: Context) {
            val movieDetailsServiceIntent = Intent(context, MovieDetailsService::class.java)
            context.stopService(movieDetailsServiceIntent)
        }
    }


}