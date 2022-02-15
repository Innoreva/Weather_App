package com.ersubhadip.instantweather.viewmodel

import android.Manifest
import android.annotation.SuppressLint
import android.app.Application
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.text.TextUtils
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.lifecycle.AndroidViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import java.util.*

class LocationViewModel(application: Application):AndroidViewModel(application) {

    @SuppressLint("StaticFieldLeak")
    private val context = getApplication<Application>().applicationContext
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private var PERMISSION_CODE = 1


    //Implemented LocationManager to get the latitude and longitude of the user
    fun getLatLong():String{

        var cityName = ""

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)

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
            .addOnSuccessListener { location : Location? ->
                // location can be null.
                if(location!=null){

                val lat = location.latitude
                val long = location.longitude
                cityName = getPlace(lat,long)
                }
            }

        Log.d("CITY",cityName) //junk code to test the code

          return cityName

    }

//    Implemented Geocoder to get the cityName related to corresponding Latitude and Longitude

    private fun getPlace(lat:Double, long:Double):String{
        val addresses: List<Address>
        val geocoder: Geocoder = Geocoder(context, Locale.getDefault())

        addresses = geocoder.getFromLocation(
            lat,
            long,
            10
        ) // Here 1 represent max location result to returned, by documents it recommended 1 to 5

        for(address in addresses){
            if(address!=null && TextUtils.isEmpty(address.toString())){
                return address.locality
            }
        }

        return ""

    }
}