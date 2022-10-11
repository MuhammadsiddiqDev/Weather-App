package uz.isystem.weatherapp.core.moduls.weather.modul


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Weather(
    @SerializedName("description")
    val description: String, // moderate rain
    @SerializedName("icon")
    val icon: String, // 10d
    @SerializedName("id")
    val id: Int, // 501
    @SerializedName("main")
    val main: String // Rain
)