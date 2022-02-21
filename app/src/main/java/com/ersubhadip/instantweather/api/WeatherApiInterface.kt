package com.ersubhadip.instantweather.api

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ersubhadip.instantweather.pojos.CurrentModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiInterface {

    @GET("v1/current.json?")
    suspend fun getCurrent(@Query("key") key:String,@Query("q") q:String,@Query("aqi") aqi:String): Response<CurrentModel>

    //todo : also make forecast function
}