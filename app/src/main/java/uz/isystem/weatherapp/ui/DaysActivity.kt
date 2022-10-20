package uz.isystem.weatherapp.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import uz.isystem.currency.network.servise.WeatherService
import uz.isystem.currency.network.servise.connection.NetworkConnection
import uz.isystem.weatherapp.BuildConfig
import uz.isystem.weatherapp.core.adapter.SliderVerticalAdapter
import uz.isystem.weatherapp.core.cache.MemoryHelper
import uz.isystem.weatherapp.core.cache.UserData
import uz.isystem.weatherapp.core.moduls.weather.hourlyModul.DailyResponse
import uz.isystem.weatherapp.databinding.ActivityDaysBinding

class DaysActivity : AppCompatActivity() {

    lateinit var sliderAdapter: SliderVerticalAdapter
    private var userData: UserData = MemoryHelper.helper!!.getLatData()
    private var latId: Double? = null
    private var lonId: Double? = null


    private var _binding: ActivityDaysBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDaysBinding.inflate(layoutInflater)
        setContentView(binding.root)

        latId = userData.latitude.toDouble()
        lonId = userData.longitude.toDouble()

        val connection = NetworkConnection.getRetrofit()
        val serviceForecast: WeatherService = connection.create(WeatherService::class.java)

        SimilarMoviesRecyclerView()

        val result2 = serviceForecast.getForecastWeather(
            units = "metric", lt = latId!!, ln = lonId!!, apiKey = BuildConfig.apiKey
        )

        result2.enqueue(object : Callback<DailyResponse> {
            override fun onResponse(
                call: Call<DailyResponse>,
                response: Response<DailyResponse>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        setSlider(it)
                        binding.progressBar.visibility = View.INVISIBLE

                    }
                } else {
                    Log.d("ttt", "${response.errorBody().toString()}")
                }

            }

            override fun onFailure(call: Call<DailyResponse>, t: Throwable) {
                Log.d("ttt", "${t.message}")
            }

        })
    }

    private fun SimilarMoviesRecyclerView() {
        sliderAdapter = SliderVerticalAdapter()
        binding.childList.adapter = sliderAdapter
        var layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.childList.layoutManager = layoutManager
    }

    fun setSlider(dailyResponse: DailyResponse) {
        sliderAdapter.setData(dailyResponse.list)
    }

}
