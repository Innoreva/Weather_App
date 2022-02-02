package com.ersubhadip.instantweather.viewmodel

import com.ersubhadip.instantweather.Constants
import com.ersubhadip.instantweather.api.RetrofitInstance
import com.ersubhadip.instantweather.pojos.CurrentModel
import retrofit2.Response

class ApiRepository(val api:RetrofitInstance) {

    suspend fun getCurrentWeather():Response<CurrentModel>{

        return api.api.getCurrent(Constants.API_KEY,"Ranchi","yes");
    }

}