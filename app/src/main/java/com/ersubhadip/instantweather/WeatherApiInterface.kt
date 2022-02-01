package com.ersubhadip.instantweather

import com.ersubhadip.instantweather.pojos.Current
import retrofit2.http.GET

interface WeatherApiInterface {
    //todo : use correct headers :-> filhaal kaam chalane ke liye 😅
    ///current.json?key=1ab25b6036044fc0bf5122216220102&q=kanpur&aqi=yes
    @GET("/current.json")
    suspend fun getCurrent() : Current

    //todo : also make forecast function
}