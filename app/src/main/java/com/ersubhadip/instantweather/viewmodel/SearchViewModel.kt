package com.ersubhadip.instantweather.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ersubhadip.instantweather.pojos.CurrentModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SearchViewModel(private val repository: ApiRepository, private val context: Application?) :
    ViewModel() {

    //input live data
    val input = MutableLiveData<String>()


    //currentWeather Live data
    private var weatherDetails = MutableLiveData<CurrentModel>()
    val finalWeatherReport: LiveData<CurrentModel>
        get() = weatherDetails

    //Location Live data
    private var receivedLocation = MutableLiveData<String>()
    val finalLocation: LiveData<String>
        get() = receivedLocation

    //Url Live data
    private var url = MutableLiveData<String>()
    val imageUrl: LiveData<String>
        get() = url


    suspend fun getWeather() {

        val response = input.value?.let { repository.getCurrentWeather(it, "yes") }
        withContext(Dispatchers.Main)
        {
            try {
                weatherDetails.value = response?.body()
                withContext(Dispatchers.Main) {
                    receivedLocation.value =
                        "${weatherDetails.value!!.location.name}, ${weatherDetails.value!!.location.country}"
                    url.value = "https:${weatherDetails.value!!.current.condition.icon}"
                }
            } catch (e: Exception) {
                url.value = "null"
                input.value = ""
            }
        }
    }
}
