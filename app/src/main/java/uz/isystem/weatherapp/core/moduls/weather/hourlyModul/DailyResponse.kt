package uz.isystem.weatherapp.core.moduls.weather.hourlyModul


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class DailyResponse(
    @SerializedName("city")
    val city: City,
    @SerializedName("cnt")
    val cnt: Int, // 40
    @SerializedName("cod")
    val cod: String, // 200
    @SerializedName("list")
    val list: List<DataResponse>,
    @SerializedName("message")
    val message: Int // 0
)