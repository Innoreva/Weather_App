package com.ersubhadip.instantweather.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class SearchViewModelFactory(
    private val apiRepo: ApiRepository,
    private val context: Application?
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SearchViewModel::class.java)) {

            return SearchViewModel(apiRepo, context) as T
        }
        throw IllegalArgumentException("Class Not Found")
    }
}