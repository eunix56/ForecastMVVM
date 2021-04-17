package eunix56.example.com.forecastmvvm.internal

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ListConverter{
    private val gson = Gson()

    @TypeConverter
    fun fromListtoString(stringList: List<String>?): String {
        if (stringList == null || stringList.isEmpty())
            return ""
        return gson.toJson(stringList)
    }

    @TypeConverter
    fun fromStringtoList(string: String?): List<String> {
        if (string == null)
            return emptyList()

        val listType = object : TypeToken<List<String>>() {}.type

        return gson.fromJson<List<String>>(string, listType)
    }
}