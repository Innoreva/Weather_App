package com.ersubhadip.instantweather.viewmodel

import com.ersubhadip.instantweather.Constants
import com.ersubhadip.instantweather.api.RetrofitInstance
import com.ersubhadip.instantweather.pojos.CurrentModel
import com.ersubhadip.instantweather.pojos.ForecastModel
import retrofit2.Response

class ApiRepository(private val api: RetrofitInstance) {
    suspend fun getCurrentWeather(
        q: String, aqi: String
    ): Response<CurrentModel> {
        return api.apiInstance.getCurrent(Constants.API_KEY, q, aqi)
    }

    suspend fun getForecast(
        q: String, days: Int, aqi: String, alerts: String
    ): Response<ForecastModel> {

        return api.apiInstance.getForecast(Constants.API_KEY, q, days, aqi, alerts)
    }

}
