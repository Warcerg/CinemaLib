package com.example.cinemalib.model.received_data

import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

object ApiUtils {
    private val baseUrlMainPart = "https://api.themoviedb.org/"
    private val baseUrlVersion = "3/"
    private val baseUrlReqType = "movie/"
    private val baseUrlReqTypePerson = "person/"
    private val posterUrlMainPArt = "https://image.tmdb.org/t/p/w500"
    val baseUrl = "$baseUrlMainPart$baseUrlVersion$baseUrlReqType"
    val baseUrlPerson = "$baseUrlMainPart$baseUrlVersion$baseUrlReqTypePerson"
    val posterUrl = posterUrlMainPArt
    const val APIKEY = "cb37ac22ea3216d2a5291d87e4c14152"

    fun getOkHTTPBuilder(): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
        httpClient.connectTimeout(10, TimeUnit.SECONDS)
        httpClient.readTimeout(10, TimeUnit.SECONDS)
        httpClient.writeTimeout(10, TimeUnit.SECONDS)
        httpClient.addInterceptor { chain ->
            val original = chain.request()
            val request = original.newBuilder()
                .method(original.method(), original.body())
                .build()

            chain.proceed(request)
        }

        return httpClient.build()
    }
}