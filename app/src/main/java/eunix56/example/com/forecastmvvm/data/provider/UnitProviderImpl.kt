package eunix56.example.com.forecastmvvm.data.provider

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.ListPreference
import androidx.preference.PreferenceManager
import eunix56.example.com.forecastmvvm.internal.UnitSystem
import eunix56.example.com.forecastmvvm.internal.convertUnitStringToUnitSystem

const val UNIT_SYSTEM = "UNIT_SYSTEM"

class UnitProviderImpl(
    context: Context
) : PreferenceProvider(context), UnitProvider {

    override fun getUnitSystem(): UnitSystem {
        val selectedName = preferences.getString(UNIT_SYSTEM, UnitSystem.METRIC.name)
        return UnitSystem.valueOf(selectedName!!)
    }

    override fun hasUnitSystemChanged(lastUnitEntry: String): Boolean {
        val unitSystemEntry = getUnitSystem()
        val lastUnitSystemEntry = convertUnitStringToUnitSystem(lastUnitEntry)
        return unitSystemEntry != lastUnitSystemEntry
    }

}