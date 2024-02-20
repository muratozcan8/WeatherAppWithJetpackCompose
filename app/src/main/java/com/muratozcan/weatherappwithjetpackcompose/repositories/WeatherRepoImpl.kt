package com.muratozcan.weatherappwithjetpackcompose.repositories

import com.muratozcan.weatherappwithjetpackcompose.models.BaseModel
import com.muratozcan.weatherappwithjetpackcompose.models.DailyForecasts
import com.muratozcan.weatherappwithjetpackcompose.models.HourlyForecast
import com.muratozcan.weatherappwithjetpackcompose.models.Location
import com.muratozcan.weatherappwithjetpackcompose.network.Api
import retrofit2.Response

class WeatherRepoImpl(private val api: Api): WeatherRepo {
    override suspend fun searchLocation(query: String): BaseModel<List<Location>> {
        return request {
            api.searchLocation(query = query)
        }
    }

    override suspend fun getDailyForecasts(locationKey: String): BaseModel<DailyForecasts> {
        return request {
            api.getDailyForecast(locationKey = locationKey)
        }
    }

    override suspend fun getHourlyForecasts(locationKey: String): BaseModel<List<HourlyForecast>> {
        return request {
            api.getHourlyForecast(locationKey = locationKey)
        }
    }
}
suspend fun<T> request(request: suspend ()->Response<T>):BaseModel<T>{
    try {
        request().also {
            return if (it.isSuccessful){
                BaseModel.Success(it.body()!!)
            } else {
                BaseModel.Error(it.errorBody()?.string().toString())
            }
        }
    } catch (e:Exception){
        return BaseModel.Error(e.message.toString())
    }
}