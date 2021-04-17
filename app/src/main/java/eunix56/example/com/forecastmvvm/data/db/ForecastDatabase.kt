package eunix56.example.com.forecastmvvm.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import eunix56.example.com.forecastmvvm.data.db.entity.CurrentWeatherEntry
import eunix56.example.com.forecastmvvm.data.db.entity.UnitEntry
import eunix56.example.com.forecastmvvm.data.db.entity.WeatherLocation
import eunix56.example.com.forecastmvvm.internal.ListConverter

@Database(
    entities = [CurrentWeatherEntry::class, UnitEntry::class, WeatherLocation::class],
    version = 1
)
@TypeConverters(ListConverter::class)
abstract class ForecastDatabase: RoomDatabase() {
    abstract fun currentWeatherDao(): CurrentWeatherDao
    abstract fun unitEntryDao(): UnitEntryDao
    abstract fun weatherLocationDao(): WeatherLocationDao

    companion object {
        @Volatile private var instance: ForecastDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) =
                Room.databaseBuilder(context.applicationContext,
                    ForecastDatabase::class.java, "forecast.db")
                    .build()
    }
}