package eunix56.example.com.forecastmvvm.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

const val CURRENT_UNIT_ENTRY_ID = 0

@Entity(tableName = "unit_entry")
data class UnitEntry(
    @SerializedName("unit")
    val unitValue: String
) {
    @PrimaryKey(autoGenerate = false)
    var id:Int = CURRENT_UNIT_ENTRY_ID
}