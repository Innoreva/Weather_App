package com.ersubhadip.instantweather.pojos

data class ForecastModel(

    val current: Current,
    val forecast: Forecast,
    val location: Location
)