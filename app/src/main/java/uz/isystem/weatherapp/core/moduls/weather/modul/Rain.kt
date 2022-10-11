package uz.isystem.weatherapp.core.moduls.weather.modul


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Rain(
    @SerializedName("1h")
    val h: Double // 3.16
)