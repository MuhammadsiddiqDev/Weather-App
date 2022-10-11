package uz.isystem.weatherapp.core.moduls.weather.hourlyModul


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Clouds(
    @SerializedName("all")
    val all: Int // 100
)