package com.ersubhadip.instantweather.viewmodel

import android.app.Application
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ersubhadip.instantweather.pojos.AirQuality
import com.ersubhadip.instantweather.pojos.Condition
import com.ersubhadip.instantweather.pojos.Current
import com.ersubhadip.instantweather.pojos.CurrentModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*

class MainViewModel(private val repository: ApiRepository, val context: Application?) :
    ViewModel() {

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    //currentWeather Live data
    private var weatherDetails = MutableLiveData<CurrentModel>()
    val finalWeatherReport: LiveData<CurrentModel>
        get() = weatherDetails

    //Location Live data
    private var receivedLocation = MutableLiveData<String>()

    val finalLocation: LiveData<String>
        get() = receivedLocation


    //Current LiveData
    private var current = MutableLiveData<Current>()

    val currentWeather: LiveData<Current>
        get() = current

    //Temperature LiveData
    private var temp = MutableLiveData<Double>()
    val temperature: LiveData<Double>
        get() = temp


    //AirQuality LiveData
    private var air = MutableLiveData<AirQuality>()
    val airQuality: LiveData<AirQuality>
        get() = air

    //Time LiveData
    private var time = MutableLiveData<String>()
    val currentTime: LiveData<String>
        get() = time

    //IconUrl LiveData
    private var iconUrl = MutableLiveData<String>()
    val url: LiveData<String>
        get() = iconUrl

    //Condition Livedata
    private var cond = MutableLiveData<Condition>()
    val condition: LiveData<Condition>
        get() = cond


    //Last Update time
    private var updTime = MutableLiveData<String>()
    val updateTime: LiveData<String>
        get() = updTime

    //City
    private var city = MutableLiveData<String>()
    val liveCity: LiveData<String>
        get() = city


    //default values
    init {
        updTime.value = "not found "
        getLatLong()
    }

    //Implemented LocationManager to get the latitude and longitude of the user
    private fun getLatLong() {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(context!!)

        fusedLocationClient.lastLocation
            .addOnSuccessListener { location: Location? ->
                // location can be null.
                Log.d("LOC#", location.toString())
                val lat = location!!.latitude
                val long = location.longitude
                getPlace(lat, long)
            }

    }

    //    Implemented Geocoder to get the cityName related to corresponding Latitude and Longitude
    private fun getPlace(lat: Double, long: Double) {
        val addresses: List<Address>
        val geocoder = Geocoder(context, Locale.getDefault())

        addresses = geocoder.getFromLocation(
            lat,
            long,
            1
        )

        city.value = addresses.first().locality

    }

    suspend fun getCurrentWeatherVM() {

        val response = city.value?.let {Log.d("!@#",it); repository.getCurrentWeather(it, "yes") }
        withContext(Dispatchers.Main) {
            if (response != null) {
                if (response.isSuccessful) {

                    weatherDetails.value = response.body()

                    //Getting Data to LiveData variables
                    receivedLocation.value =
                        "${weatherDetails.value!!.location.name}, ${weatherDetails.value!!.location.country}"
                    temp.value = weatherDetails.value!!.current.temp_c
                    time.value = weatherDetails.value!!.location.localtime
                    air.value = weatherDetails.value!!.current.air_quality
                    current.value = weatherDetails.value!!.current
                    cond.value = weatherDetails.value!!.current.condition
                    iconUrl.value = "https:${weatherDetails.value!!.current.condition.icon}"
                    updTime.value = "Last Updated: ${weatherDetails.value!!.current.last_updated}"
                    //end

                } else {
                    withContext(Dispatchers.Main) {
                        showToast("Something went wrong", Toast.LENGTH_LONG)
                    }
                }
            }
        }
    }

    fun showToast(toastMessage: String, length: Int) {
        Toast.makeText(context, toastMessage, length).show()
    }

    suspend fun updateWeather() {
        getCurrentWeatherVM()
    }

}



