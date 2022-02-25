package com.ersubhadip.instantweather.pojos

data class Hour(
    val chance_of_rain: Int,
    val chance_of_snow: Int,
    val cloud: Int,
    val condition: Condition,
    val dewpoint_c: Double,
    val feelslike_c: Double,
    val heatindex_c: Double,
    val humidity: Int,
    val is_day: Int,
    val precip_in: Double,
    val pressure_mb: Double,
    val temp_c: Double,
    val time: String,
    val time_epoch: Int,
    val vis_km: Double,
    val will_it_rain: Int,
    val will_it_snow: Int,
    val wind_degree: Int,
    val wind_dir: String,
    val wind_kph: Double,
    val windchill_c: Double
)
fun Hour.toForecastAdapterModel():ForecastAdapterModel = ForecastAdapterModel(time,temp_c.toString(),condition.icon)