package com.ersubhadip.instantweather.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ersubhadip.instantweather.Constants
import com.ersubhadip.instantweather.api.RetrofitInstance
import com.ersubhadip.instantweather.pojos.CurrentModel
import retrofit2.Response

class ApiRepository(private val api:RetrofitInstance) {
    suspend fun getCurrentWeather(
        q:String,aqi:String
    ):Response<CurrentModel> {

        Log.d("#JatinBhai#",api.apiInstance.getCurrent(Constants.API_KEY,"Delhi","yes")

            .toString())
        return api.apiInstance.getCurrent(Constants.API_KEY,q,aqi)
    }

}
