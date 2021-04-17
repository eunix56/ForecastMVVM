package eunix56.example.com.forecastmvvm.data.repository

import androidx.lifecycle.LiveData
import eunix56.example.com.forecastmvvm.data.db.entity.CurrentWeatherEntry
import eunix56.example.com.forecastmvvm.data.db.entity.UnitEntry
import eunix56.example.com.forecastmvvm.data.db.entity.WeatherLocation

interface ForecastRepository {
    suspend fun getCurrentWeather(): LiveData<CurrentWeatherEntry>
    suspend fun getUnitEntry(): LiveData<UnitEntry>
    suspend fun getWeatherLocation(): LiveData<WeatherLocation>
}