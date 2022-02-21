package com.ersubhadip.instantweather.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.helper.widget.Carousel
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ersubhadip.instantweather.R


private var rvNextDayWeatherForecast: RecyclerView? = null

class ForecastFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

//        val forecastAdapter = ForecastAdapter(requireContext())
        val layoutManager = LinearLayoutManager(requireContext())

        rvNextDayWeatherForecast = view?.findViewById<RecyclerView>(R.id.weatherForecastRv)
//        rvNextDayWeatherForecast?.adapter = forecastAdapter
        rvNextDayWeatherForecast?.layoutManager = layoutManager



        return inflater.inflate(R.layout.fragment_forecast, container, false)

    }

}