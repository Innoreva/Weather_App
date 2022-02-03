package com.ersubhadip.instantweather.viewmodel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch


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