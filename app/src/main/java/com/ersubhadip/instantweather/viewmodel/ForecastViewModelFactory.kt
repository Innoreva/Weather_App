package com.ersubhadip.instantweather.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ForecastViewModelFactory(private val repository: ApiRepository, private val context: Application?):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ForecastViewModel::class.java)){
            return ForecastViewModel(repository,context) as T
        }
        throw IllegalArgumentException("Class Not found")
    }
}