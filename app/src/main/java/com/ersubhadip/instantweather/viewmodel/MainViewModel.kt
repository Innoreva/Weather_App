package com.ersubhadip.instantweather.viewmodel

import android.Manifest
import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import androidx.core.app.ActivityCompat
import androidx.lifecycle.*
import com.ersubhadip.instantweather.pojos.AirQuality
import com.ersubhadip.instantweather.pojos.Condition
import com.ersubhadip.instantweather.pojos.Current
import com.ersubhadip.instantweather.pojos.CurrentModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.launch
import retrofit2.Response
import java.util.*


class MainViewModel(val repository: ApiRepository,val context: Context?) :
    ViewModel() {

    @SuppressLint("StaticFieldLeak")
//    private val context = getApplication<Application>().applicationContext
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private var PERMISSION_CODE = 1

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


    //default values
    init {
        receivedLocation.value = "Delhi"
        updTime.value = "not fetched"
    }

    //Implemented LocationManager to get the latitude and longitude of the user
    fun getLatLong(): String {

        var city = "Delhi"

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(context!!)

        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {

            PERMISSION_CODE = 2
            //TODO:CASE HANDLING FOR NOT GRANTING PERMISSION
        }
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location: Location? ->
                // location can be null.
                if (location != null) {

                    val lat = location.latitude
                    val long = location.longitude
                    city = getPlace(lat, long)
                }
            }

        Log.d("CITY", city) //junk code to test the code

        return city
    }

//    Implemented Geocoder to get the cityName related to corresponding Latitude and Longitude

    private fun getPlace(lat: Double, long: Double): String {
        val addresses: List<Address>
        val geocoder = Geocoder(context, Locale.getDefault())

        addresses = geocoder.getFromLocation(
            lat,
            long,
            1
        )


        Log.i("City",addresses.first().locality)
        return addresses.first().locality

    }

    fun getCurrentWeatherVM() {

        viewModelScope.launch {
            val response = repository.getCurrentWeather("Dhanbad", "yes")


            if (response.isSuccessful) {

                weatherDetails.value = response.body()

                //Getting Data to LiveData variables
                receivedLocation.value = weatherDetails.value!!.location.name
                temp.value = weatherDetails.value!!.current.temp_c
                time.value = weatherDetails.value!!.location.localtime
                air.value = weatherDetails.value!!.current.air_quality
                current.value = weatherDetails.value!!.current
                cond.value = weatherDetails.value!!.current.condition
                iconUrl.value = weatherDetails.value!!.current.condition.icon
                updTime.value = "Last Updated: ${weatherDetails.value!!.current.last_updated}"
                //end

            } else {
                Toast.makeText(context, "Something Went Wrong - API", Toast.LENGTH_LONG).show()
            }

        }


    }

    fun updateWeather(){

        //todo:fake 1000ms loading
        getCurrentWeatherVM()
    }



    // todo:Null Response (404), Permissions (check and inflate Permission Denied), No internet -> Try Again (last implementation)
}

