package com.ersubhadip.instantweather.api

import com.ersubhadip.instantweather.pojos.CurrentModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.Headers
import retrofit2.http.Query

interface WeatherApiInterface {
    //todo : use correct headers :-> filhaal kaam chalane ke liye 😅
    ///current.json?key=1ab25b6036044fc0bf5122216220102&q=kanpur&aqi=yes
    //@GET("/current.json")
    //@GET("/current.json?key=1ab25b6036044fc0bf5122216220102&q=kanpur&aqi=no")
    /*var vibhuMap: Map<String, String>
        get() = mapOf<String,String>(
            "key" to "1ab25b6036044fc0bf5122216220102",
            "q" to "Kanpur",
            "aqi" to "no"
        )
        set(value) =

    @GET("/current.json")
    suspend fun getCurrent(@HeaderMap Map<String,String> vibhuMap) : Current
    */

    /*@Headers(
        "key: 1ab25b6036044fc0bf5122216220102",
        "q: kanpur",
        "aqi: no"
    )
    @GET("/v1/current.json")
    suspend fun getCurrent(): Response<Current>
     */
    @GET("/v1/current.json")
    suspend fun getCurrent(@Query("key") key:String,@Query("q") q:String,@Query("aqi") aqi:String): Response<CurrentModel>

    //todo : also make forecast function
}