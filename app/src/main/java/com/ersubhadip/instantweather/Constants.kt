package com.ersubhadip.instantweather

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.os.Build
import androidx.core.content.getSystemService
import com.ersubhadip.instantweather.Constants.Companion.API_KEY
import com.ersubhadip.instantweather.system.NetworkInstance.networkRequest

class Constants {
    companion object{
        const val API_URL = "https://api.weatherapi.com/"
        const val API_KEY = BuildConfig.API_KEY

    }
    fun checkInternet(context:Context): Boolean{
        val networkCallback = object : ConnectivityManager.NetworkCallback(){
            override fun onAvailable(network: Network) {
                super.onAvailable(network)
            }

            override fun onCapabilitiesChanged(
                network: Network,
                networkCapabilities: NetworkCapabilities
            ) {
                super.onCapabilitiesChanged(network, networkCapabilities)
            }

            override fun onLost(network: Network) {
                super.onLost(network)
            }
        }

        val connectivityManager = context.getSystemService(ConnectivityManager::class.java)
        connectivityManager.requestNetwork(networkRequest,networkCallback)

        //todo : Improve this code with more research(remove deprecation)
        val networkInfo = connectivityManager.activeNetworkInfo?:return false
        return networkInfo.isConnected
    }
}