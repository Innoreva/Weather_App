package com.ersubhadip.instantweather.viewmodel

import android.Manifest
import android.app.Application
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ersubhadip.instantweather.pojos.Forecastday
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.launch
import java.util.*

class ForecastViewModel(private val repository: ApiRepository, private val context: Application?) :
    ViewModel() {

    private var PERMISSION_CODE = 1
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    //Forecast Model Live Data
    private var forecast = MutableLiveData<List<Forecastday>>()
    val forecastDetails: LiveData<List<Forecastday>>
        get() = forecast

    //City
    private var city = MutableLiveData<String>()
    val liveCity: LiveData<String>
        get() = city

    //default values
    init {
        getLatLong()
    }

    //Implemented LocationManager to get the latitude and longitude of the user
    fun getLatLong() {

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(context!!)

        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {

            Toast.makeText(
                context,
                "NO Permission, Please Grant the Location permission",
                Toast.LENGTH_LONG
            ).show()

            PERMISSION_CODE = 2
            //TODO:CASE HANDLING FOR NOT GRANTING PERMISSION - Redirect to Settings
        }

        Log.d("CODE_#", PERMISSION_CODE.toString())

        fusedLocationClient.lastLocation
            .addOnSuccessListener { location: Location? ->
                // location can be null.
                Log.d("LOC#", location.toString())
                var lat = location!!.latitude
                var long = location.longitude

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


//Getting Forecast Data for Today
    fun getForecast() {
        viewModelScope.launch {
            val response = repository.getForecast("Dhanbad", 1, "yes", "no")

            if (response != null) {
                if (response.isSuccessful) {

                    forecast.value = response.body()?.forecast?.forecastday


                } else {
                    Toast.makeText(
                        context,
                        "Something Went Wrong! Check your Internet",
                        Toast.LENGTH_LONG
                    ).show()
                }
            } else {

                Toast.makeText(
                    context,
                    "Something Went Wrong! Check your Permissions",
                    Toast.LENGTH_LONG
                ).show()
            }

        }
    }
}