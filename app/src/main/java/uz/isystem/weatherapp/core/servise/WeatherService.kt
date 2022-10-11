package uz.isystem.currency.network.servise

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import uz.isystem.weatherapp.core.moduls.weather.hourlyModul.DailyResponse
import uz.isystem.weatherapp.core.moduls.weather.modul.CurrentWeather

interface WeatherService {

//    https://api.openweathermap.org/data/2.5/weather?lat={lat}&lon={lon}&appid={API key}

    @GET("data/2.5/weather")
    fun getCurrentWeather(
        @Query("units") units: String,
        @Query("lat") lt: Double,
        @Query("lon") ln: Double,
        @Query("appid") apiKey: String
    ): Call<CurrentWeather>

//   api.openweathermap.org/data/2.5/forecast?lat={lat}&lon={lon}&appid={API key}

    @GET("data/2.5/forecast")
    fun getForecastWeather(
        @Query("units") units: String,
        @Query("lat") lt: Double,
        @Query("lon") ln: Double,
        @Query("appid") apiKey: String
    ): Call<DailyResponse>
}