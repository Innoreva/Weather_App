package com.ersubhadip.instantweather.pojos

data class Day(
    val avghumidity: Double,
    val avgtemp_c: Double,
    val avgvis_km: Double,
    val condition: Condition,
    val daily_chance_of_rain: Int,
    val daily_chance_of_snow: Int,
    val daily_will_it_rain: Int,
    val daily_will_it_snow: Int,
    val maxtemp_c: Double,
    val maxwind_kph: Double,
    val mintemp_c: Double,
    val totalprecip_in: Double
)