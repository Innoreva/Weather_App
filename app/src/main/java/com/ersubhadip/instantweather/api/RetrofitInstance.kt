package com.ersubhadip.instantweather.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    val api: WeatherApiInterface by lazy{
        Retrofit.Builder()
            .baseUrl("http://api.weatherapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherApiInterface::class.java)
    }
}