package com.muratozcan.weatherappwithjetpackcompose.network

import com.muratozcan.weatherappwithjetpackcompose.models.DailyForecasts
import com.muratozcan.weatherappwithjetpackcompose.models.HourlyForecast
import com.muratozcan.weatherappwithjetpackcompose.models.Location
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.Properties

interface Api {
     @GET("locations/v1/cities/search")
     suspend fun searchLocation(
         @Query("apikey") apiKey: String = getApiKey(),
         @Query("q") query: String
     ):Response<List<Location>>

     @GET("forecasts/v1/daily/5day/{location_key}")
     suspend fun getDailyForecast(
         @Path("location_key") locationKey: String,
         @Query("apikey") apiKey: String = getApiKey(),
         @Query("metric") metric: Boolean = true
     ): Response<DailyForecasts>

     @GET("forecasts/v1/hourly/12hour/{location_key}")
     suspend fun getHourlyForecast(
         @Path("location_key") locationKey: String,
         @Query("apikey") apiKey: String = getApiKey(),
         @Query("metric") metric: Boolean = true
     ): Response<List<HourlyForecast>>

    fun getApiKey(): String {
        val properties = Properties()
        val inputStream = this::class.java.classLoader?.getResourceAsStream("local.properties")
        properties.load(inputStream)
        return properties.getProperty("API_KEY")
    }
}

