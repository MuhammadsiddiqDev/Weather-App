package uz.isystem.weatherapp.core.moduls.weather.hourlyModul


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Sys(
    @SerializedName("pod")
    val pod: String // d
)