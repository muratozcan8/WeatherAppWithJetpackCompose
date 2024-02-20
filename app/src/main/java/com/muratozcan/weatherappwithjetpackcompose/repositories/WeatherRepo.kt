package com.muratozcan.weatherappwithjetpackcompose.repositories

import com.muratozcan.weatherappwithjetpackcompose.models.BaseModel
import com.muratozcan.weatherappwithjetpackcompose.models.DailyForecasts
import com.muratozcan.weatherappwithjetpackcompose.models.HourlyForecast
import com.muratozcan.weatherappwithjetpackcompose.models.Location

interface WeatherRepo {
    suspend fun searchLocation(query:String):BaseModel<List<Location>>
    suspend fun getDailyForecasts(locationKey:String):BaseModel<DailyForecasts>
    suspend fun getHourlyForecasts(locationKey: String):BaseModel<List<HourlyForecast>>
}