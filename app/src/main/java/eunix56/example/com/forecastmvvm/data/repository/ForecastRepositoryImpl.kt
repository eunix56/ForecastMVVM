package eunix56.example.com.forecastmvvm.data.repository

import androidx.lifecycle.LiveData
import eunix56.example.com.forecastmvvm.data.db.CurrentWeatherDao
import eunix56.example.com.forecastmvvm.data.db.UnitEntryDao
import eunix56.example.com.forecastmvvm.data.db.WeatherLocationDao
import eunix56.example.com.forecastmvvm.data.db.entity.CurrentWeatherEntry
import eunix56.example.com.forecastmvvm.data.db.entity.UnitEntry
import eunix56.example.com.forecastmvvm.data.db.entity.WeatherLocation
import eunix56.example.com.forecastmvvm.data.network.WeatherNetworkDataSource
import eunix56.example.com.forecastmvvm.data.network.response.CurrentWeatherResponse
import eunix56.example.com.forecastmvvm.data.provider.LocationProvider
import eunix56.example.com.forecastmvvm.data.provider.UnitProvider
import eunix56.example.com.forecastmvvm.internal.convertUnitSystemToUnitString
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.threeten.bp.ZonedDateTime
import java.util.*

class ForecastRepositoryImpl(
    private val currentWeatherDao: CurrentWeatherDao,
    private val weatherNetworkDataSource: WeatherNetworkDataSource,
    private val unitEntryDao: UnitEntryDao,
    private val weatherLocationDao: WeatherLocationDao,
    private val unitProvider: UnitProvider,
    private val locationProvider: LocationProvider
) : ForecastRepository {

    init {
        weatherNetworkDataSource.downloadedCurrentWeather.observeForever { newCurrentWeather ->
            persistFetchedCurrentWeather(newCurrentWeather)
        }
    }

    override suspend fun getCurrentWeather(): LiveData<CurrentWeatherEntry> {
        initWeatherData()
       return withContext(Dispatchers.IO) {
           return@withContext currentWeatherDao.getWeatherEntry()
       }
    }

    override suspend fun getUnitEntry(): LiveData<UnitEntry> {
        return withContext(Dispatchers.IO) {
            return@withContext unitEntryDao.getUnitEntry()
        }
    }

    override suspend fun getWeatherLocation(): LiveData<WeatherLocation> {
        return withContext(Dispatchers.IO) {
            return@withContext weatherLocationDao.getLocation()
        }
    }

    private fun persistFetchedCurrentWeather(fetchedWeather: CurrentWeatherResponse) {
        GlobalScope.launch(Dispatchers.IO) {
            currentWeatherDao.upsert(fetchedWeather.currentWeatherEntry)
            unitEntryDao.upsert(fetchedWeather.unitEntry)
            weatherLocationDao.upsert(fetchedWeather.location)
        }
    }

    private suspend fun initWeatherData() {
        val lastUnitSystem = unitEntryDao.getUnitEntry().value
        val lastWeatherLocation = weatherLocationDao.getLocation().value

        if (lastWeatherLocation == null || lastUnitSystem == null
            || locationProvider.hasLocationChanged(lastWeatherLocation)
            ||unitProvider.hasUnitSystemChanged(lastUnitSystem.unitValue)) {
            fetchCurrentWeather()
            return
        }
        if (isFetchCurrentWeatherNeeded(lastWeatherLocation.zonedDateTime))
            fetchCurrentWeather()
    }

    private suspend fun fetchCurrentWeather() {
        weatherNetworkDataSource.fetchCurrentWeather(
            locationProvider.getPreferredLocation(),
            Locale.getDefault().language,
            convertUnitSystemToUnitString(unitProvider.getUnitSystem())
        )
    }

    private fun isFetchCurrentWeatherNeeded(lastFetchTime: ZonedDateTime): Boolean {
        val thirtyMinutesAgo = ZonedDateTime.now().minusMinutes(30)
        return lastFetchTime.isBefore(thirtyMinutesAgo)
    }
}