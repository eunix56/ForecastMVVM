package eunix56.example.com.forecastmvvm.internal

fun convertUnitStringToUnitSystem(lastUnitString: String): UnitSystem{
    var unitEntry = UnitSystem.METRIC
    when(lastUnitString){
        "m" -> unitEntry = UnitSystem.METRIC
        "s" -> unitEntry = UnitSystem.SCIENTIFIC
        "f" -> unitEntry = UnitSystem.IMPERIAL
    }
    return unitEntry
}

fun convertUnitSystemToUnitString(lastUnitEntry: UnitSystem): String{
    var unitString = "m"
    when(lastUnitEntry){
        UnitSystem.METRIC -> unitString = "m"
        UnitSystem.SCIENTIFIC -> unitString = "s"
        UnitSystem.IMPERIAL -> unitString = "f"
    }
    return unitString
}

operator fun Double.minus(lat: String): Double {
    return lat.toDouble()
}