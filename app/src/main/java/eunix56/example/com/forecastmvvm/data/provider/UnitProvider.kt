package eunix56.example.com.forecastmvvm.data.provider

import eunix56.example.com.forecastmvvm.internal.UnitSystem

interface UnitProvider {
    fun getUnitSystem(): UnitSystem
    fun hasUnitSystemChanged(lastUnitEntry: String): Boolean
}