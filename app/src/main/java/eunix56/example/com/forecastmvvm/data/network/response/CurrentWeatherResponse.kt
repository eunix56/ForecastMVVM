package eunix56.example.com.forecastmvvm.data.network.response

import com.google.gson.annotations.SerializedName
import eunix56.example.com.forecastmvvm.data.db.entity.CurrentWeatherEntry
import eunix56.example.com.forecastmvvm.data.db.entity.WeatherLocation
import eunix56.example.com.forecastmvvm.data.db.entity.UnitEntry


data class CurrentWeatherResponse(
    @SerializedName("current")
    val currentWeatherEntry: CurrentWeatherEntry,
    val location: WeatherLocation,
    @SerializedName("request")
    val unitEntry: UnitEntry
)