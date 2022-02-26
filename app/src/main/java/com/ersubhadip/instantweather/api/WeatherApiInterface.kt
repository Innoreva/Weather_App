package com.ersubhadip.instantweather.api

import com.ersubhadip.instantweather.pojos.CurrentModel
import com.ersubhadip.instantweather.pojos.ForecastModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiInterface {

    //Live Weather Query
    @GET("v1/current.json")
    suspend fun getCurrent(
        @Query("key") key: String,
        @Query("q") q: String,
        @Query("aqi") aqi: String
    ): Response<CurrentModel>

    //Forecast Query
    @GET("v1/forecast.json")
    suspend fun getForecast(
        @Query("key") key: String,
        @Query("q") q: String,
        @Query("days") days: Int,
        @Query("aqi") aqi: String,
        @Query("alerts") alerts: String
    ): Response<ForecastModel>
}