package com.example.cinemalib.model

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.cinemalib.model.received_data.recieved_entities.MovieListDTO
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import javax.net.ssl.HttpsURLConnection
import com.google.gson.Gson
import java.lang.Exception
import java.net.MalformedURLException
import java.util.stream.Collectors

object MovieListLoader {
    fun loadMovieList(queryMovieList: String): MovieListDTO? {
        try {
            val uri =
                URL("https://api.themoviedb.org/3/movie/${queryMovieList}?api_key=cb37ac22ea3216d2a5291d87e4c14152")

            lateinit var urlConnection: HttpsURLConnection
            try {
                urlConnection = uri.openConnection() as HttpsURLConnection
                urlConnection.requestMethod = "GET"
                urlConnection.addRequestProperty("api_key", "cb37ac22ea3216d2a5291d87e4c14152")
                urlConnection.readTimeout = 10000
                val bufferedReader = BufferedReader(InputStreamReader(urlConnection.inputStream))
                val lines = if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
                    getLinesForOld(bufferedReader)
                } else {
                    getLines(bufferedReader)
                }
                return Gson().fromJson(lines, MovieListDTO::class.java)
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                urlConnection.disconnect()
            }
        } catch (e: MalformedURLException) {
            e.printStackTrace()
        }

        return null
    }

    private fun getLinesForOld(reader: BufferedReader): String {
        val rawData = StringBuilder(1024)
        var tempVariable: String?

        while (reader.readLine().also { tempVariable = it } != null) {
            rawData.append(tempVariable).append("\n")
        }

        reader.close()
        return rawData.toString()
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun getLines(reader: BufferedReader): String {
        return reader.lines().collect(Collectors.joining("\n"))
    }


}