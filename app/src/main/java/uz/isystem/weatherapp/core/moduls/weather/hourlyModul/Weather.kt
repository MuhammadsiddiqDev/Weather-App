package uz.isystem.weatherapp.core.moduls.weather.hourlyModul


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Weather(
    @SerializedName("description")
    val description: String, // light rain
    @SerializedName("icon")
    val icon: String, // 10d
    @SerializedName("id")
    val id: Int, // 500
    @SerializedName("main")
    val main: String // Rain
)