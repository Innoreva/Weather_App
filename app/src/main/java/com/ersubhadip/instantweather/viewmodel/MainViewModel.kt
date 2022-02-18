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
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import androidx.core.app.ActivityCompat
import androidx.lifecycle.*
import com.ersubhadip.instantweather.pojos.CurrentModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.launch
import java.util.*


class MainViewModel(val repository: ApiRepository,application: Application) : AndroidViewModel(application) {

    @SuppressLint("StaticFieldLeak")
    private val context = getApplication<Application>().applicationContext
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private var PERMISSION_CODE = 1


    //Location Live data
    private var receivedLocation=MutableLiveData<String>()

    val finalLocation:LiveData<String>
    get() = receivedLocation

    //currentWeather Live data
    private var weatherDetails = MutableLiveData<CurrentModel>()

    val finalWeatherReport:LiveData<CurrentModel>
    get() = weatherDetails

    //Temperature LiveData
    private var temp = MutableLiveData<Double>()
    val temperature:LiveData<Double>
    get() = temp

    ///todo:Other related data


    //default values
    init{
       receivedLocation.value = "Delhi"
    }

    //Implemented LocationManager to get the latitude and longitude of the user
    fun getLatLong():String{

        var city = "Delhi"

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
                    city = getPlace(lat,long)
                }
            }

        Log.d("CITY",city) //junk code to test the code

        return city
    }

//    Implemented Geocoder to get the cityName related to corresponding Latitude and Longitude

    private fun getPlace(lat:Double, long:Double):String{
        val addresses: List<Address>
        val geocoder = Geocoder(context, Locale.getDefault())

        addresses = geocoder.getFromLocation(
            lat,
            long,
            10
        )

        for(address in addresses){
            if(address!=null && !(TextUtils.isEmpty(address.toString()))){

                Log.d("#CITY",address.locality)
                return address.locality
            }
        }

        return "Delhi"

    }
    suspend fun getCurrentWeatherVM() {

        viewModelScope.launch {
            val result =  repository.getCurrentWeather(getLatLong(), "yes")
            if(result.isSuccessful){

                //todo:set values to live data
                val resultBody = result.body()


            }else{
                Toast.makeText(context,"Something Went Wrong",LENGTH_LONG).show()
            }


        }
        // todo:Null Response (404), Permissions (check and inflate Permission Denied), No internet -> Try Again (last implementation)
    }


}