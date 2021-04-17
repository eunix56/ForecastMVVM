package eunix56.example.com.forecastmvvm.internal

import java.io.IOException
import java.lang.Exception

class NoConnectivityException: IOException()

class LocationPermissionNotGrantedException: Exception()