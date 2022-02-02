package com.ersubhadip.instantweather.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ersubhadip.instantweather.ui.TAG
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class MainViewModel(val repository: ApiRepository) : ViewModel() {

    suspend fun getCurrentWeatherVM() {

        viewModelScope.launch {

            val result =  repository.getCurrentWeather("Ranchi", "yes")


          if(result!=null && result.isSuccessful){

               //todo:Return in LiveData to UI
          }

        }
    }


}