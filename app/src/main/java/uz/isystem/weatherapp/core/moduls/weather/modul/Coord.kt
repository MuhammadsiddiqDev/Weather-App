package uz.isystem.weatherapp.core.moduls.weather.modul


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Coord(
    @SerializedName("lat")
    val lat: Double, // 44.34
    @SerializedName("lon")
    val lon: Double // 10.99
)