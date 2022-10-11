package uz.isystem.weatherapp.core.moduls.weather.hourlyModul


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class DataResponse(
    @SerializedName("clouds")
    val clouds: Clouds,
    @SerializedName("dt")
    val dt: Int, // 1661871600
    @SerializedName("dt_txt")
    val dtTxt: String, // 2022-08-30 15:00:00
    @SerializedName("main")
    val main: Main,
    @SerializedName("pop")
    val pop: Double, // 0.32
    @SerializedName("rain")
    val rain: Rain,
    @SerializedName("sys")
    val sys: Sys,
    @SerializedName("visibility")
    val visibility: Int, // 10000
    @SerializedName("weather")
    val weather: List<Weather>,
    @SerializedName("wind")
    val wind: Wind
)