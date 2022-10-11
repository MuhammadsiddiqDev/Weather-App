package uz.isystem.weatherapp.core.moduls.weather.modul


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Sys(
    @SerializedName("country")
    val country: String, // IT
    @SerializedName("id")
    val id: Int, // 2075663
    @SerializedName("sunrise")
    val sunrise: Int, // 1661834187
    @SerializedName("sunset")
    val sunset: Int, // 1661882248
    @SerializedName("type")
    val type: Int // 2
)