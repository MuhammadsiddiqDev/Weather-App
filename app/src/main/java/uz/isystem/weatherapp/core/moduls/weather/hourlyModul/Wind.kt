package uz.isystem.weatherapp.core.moduls.weather.hourlyModul


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Wind(
    @SerializedName("deg")
    val deg: Int, // 349
    @SerializedName("gust")
    val gust: Double, // 1.18
    @SerializedName("speed")
    val speed: Double // 0.62
)