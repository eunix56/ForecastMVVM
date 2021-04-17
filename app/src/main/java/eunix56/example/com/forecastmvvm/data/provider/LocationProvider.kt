package eunix56.example.com.forecastmvvm.data.provider

import eunix56.example.com.forecastmvvm.data.db.entity.WeatherLocation

interface LocationProvider {
    suspend fun hasLocationChanged(lastWeatherLocation: WeatherLocation): Boolean
    suspend fun getPreferredLocation(): String
}