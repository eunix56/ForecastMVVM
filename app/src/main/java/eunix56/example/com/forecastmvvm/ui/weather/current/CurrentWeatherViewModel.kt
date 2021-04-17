package eunix56.example.com.forecastmvvm.ui.weather.current

import androidx.lifecycle.ViewModel;
import eunix56.example.com.forecastmvvm.data.provider.UnitProvider
import eunix56.example.com.forecastmvvm.data.repository.ForecastRepository
import eunix56.example.com.forecastmvvm.internal.UnitSystem
import eunix56.example.com.forecastmvvm.internal.lazyDeferred

class CurrentWeatherViewModel(
    private val forecastRepository: ForecastRepository,
    unitProvider: UnitProvider
) : ViewModel() {
    private val unitSystem = unitProvider.getUnitSystem()

    val weather by lazyDeferred {
        forecastRepository.getCurrentWeather()
    }

    val weatherLocation by lazyDeferred {
        forecastRepository.getWeatherLocation()
    }

    fun getUnitSystemValue(): Int{
        return when(unitSystem) {
            UnitSystem.METRIC -> 0
            UnitSystem.SCIENTIFIC -> 1
            UnitSystem.IMPERIAL -> 2
        }
    }
}
