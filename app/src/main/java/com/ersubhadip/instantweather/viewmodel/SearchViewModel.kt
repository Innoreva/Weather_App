package com.ersubhadip.instantweather.viewmodel

import android.app.Application
import android.widget.Toast
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
        if (response != null) {
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    weatherDetails.value = response.body()
                    receivedLocation.value =
                        "${weatherDetails.value!!.location.name}, ${weatherDetails.value!!.location.country}"
                    url.value = "https:${weatherDetails.value!!.current.condition.icon}"

                }
                else {

                    Toast.makeText(
                        context,
                        "Something Went Wrong. Check your input",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }
}
