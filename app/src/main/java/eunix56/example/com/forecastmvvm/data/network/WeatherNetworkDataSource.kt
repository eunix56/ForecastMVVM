package eunix56.example.com.forecastmvvm.data.network

import androidx.lifecycle.LiveData
import eunix56.example.com.forecastmvvm.data.network.response.CurrentWeatherResponse

interface WeatherNetworkDataSource {
    val downloadedCurrentWeather: LiveData<CurrentWeatherResponse>

    suspend fun fetchCurrentWeather(
        location: String,
        languageCode: String,
        unitCode: String
    )
}