package com.ersubhadip.instantweather.pojos

data class CurrentModel(  //main model we are getting in return
    val current: Current,
    val location: Location
)