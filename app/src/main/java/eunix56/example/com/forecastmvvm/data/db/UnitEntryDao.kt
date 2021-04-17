package eunix56.example.com.forecastmvvm.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import eunix56.example.com.forecastmvvm.data.db.entity.CURRENT_UNIT_ENTRY_ID
import eunix56.example.com.forecastmvvm.data.db.entity.CURRENT_WEATHER_ID
import eunix56.example.com.forecastmvvm.data.db.entity.CurrentWeatherEntry
import eunix56.example.com.forecastmvvm.data.db.entity.UnitEntry

@Dao
interface UnitEntryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(unitEntry: UnitEntry)

    @Query("select * from unit_entry where id = $CURRENT_UNIT_ENTRY_ID")
    fun getUnitEntry(): LiveData<UnitEntry>
}