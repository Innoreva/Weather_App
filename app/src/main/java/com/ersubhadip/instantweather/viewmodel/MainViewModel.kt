package com.ersubhadip.instantweather.viewmodel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ersubhadip.instantweather.pojos.CurrentModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response


class MainViewModel(val repository: ApiRepository) : ViewModel() {

    suspend fun getCurrentWeatherVM():CurrentModel {

        lateinit var ans:CurrentModel

        viewModelScope.launch {

            val result =  repository.getCurrentWeather("Ranchi", "yes")


//          if(result!=null && result.isSuccessful){
//               //todo:Return in LiveData to UI
//          }else{
//              withContext(Dispatchers.Main){
//
//              }
//          }

            ans= result.body()!!

        }

        // todo:Null Response (404), Permissions (check and inflate Permission Denied), No internet -> Try Again (last) - ignore

        return ans;

    }


}