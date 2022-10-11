package uz.isystem.weatherapp.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import uz.isystem.currency.network.servise.WeatherService
import uz.isystem.currency.network.servise.connection.NetworkConnection
import uz.isystem.weatherapp.BuildConfig
import uz.isystem.weatherapp.R
import uz.isystem.weatherapp.core.adapter.SliderHorizontalAdapter
import uz.isystem.weatherapp.core.cache.MemoryHelper
import uz.isystem.weatherapp.core.cache.UserData
import uz.isystem.weatherapp.core.moduls.weather.hourlyModul.DailyResponse
import uz.isystem.weatherapp.core.moduls.weather.modul.CurrentWeather
import uz.isystem.weatherapp.databinding.ActivityWeatherBinding
import java.text.SimpleDateFormat
import java.util.*

class WeatherActivity : AppCompatActivity() {

    private var sliderAdapter: SliderHorizontalAdapter? = null
    private var userData: UserData = MemoryHelper.helper!!.getLatData()
    private var latId: Double? = null
    private var lonId: Double? = null

    private var _binding: ActivityWeatherBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityWeatherBinding.inflate(layoutInflater)
        setContentView(binding.root)

        latId = userData.latitude.toDouble()
        lonId = userData.longitude.toDouble()

        seekBar()

        val connection = NetworkConnection.getRetrofit()
        val serviceWeather: WeatherService = connection.create(WeatherService::class.java)
        val serviceForecast: WeatherService = connection.create(WeatherService::class.java)

        binding.childList.setOnClickListener {
            var intent = Intent(this, DaysActivity::class.java)
            startActivity(intent)
        }

        binding.allButton.setOnClickListener {
            var intent = Intent(this, DaysActivity::class.java)
            startActivity(intent)

        }

        binding.addButton.setOnClickListener {
            var intent = Intent(this, MapsActivity::class.java)
            startActivity(intent)
        }

        val result = serviceWeather.getCurrentWeather(
            units = "metric", lt = latId!!, ln = lonId!!, apiKey = BuildConfig.apiKey
        )

        result.enqueue(object : Callback<CurrentWeather> {
            override fun onResponse(
                call: Call<CurrentWeather>,
                response: Response<CurrentWeather>
            ) {
                if (response.isSuccessful) {
                    var data = response.body()

                    if (data!!.main.temp < 0) {
                        binding.tempIcon.setImageResource(R.drawable.coldtemprory)
                    } else {
                        binding.tempIcon.setImageResource(R.drawable.hotemprory)
                    }
                    binding.ccc.text = "c"

//                    val dateNow =Date(data.sys.type.toLong() * 1000)
//                    val simpleDateNow = SimpleDateFormat("HH:MM")
//                    val timeNow = simpleDateNow.format(dateNow)

//                    binding.nowTime.text = "\uD83D\uDD70 ${timeNow}"

                    binding.temperature.text = "${data.main.temp.toInt()}"

                    val date1 = Date(data.sys.sunset.toLong() * 1000)
                    val sunset = date1.toString()
                    binding.sunset.text = "${sunset.substring(11, 16)}"

                    val date2 = Date(data.sys.sunrise.toLong() * 1000)
                    val sunrise = date2.toString()
                    binding.sunrise.text = "${sunrise.substring(11, 16)}"

                    val date3 = Date(data.dt.toLong() * 1000)
                    val time = date3.toString()

                    val kk = data.sys.sunrise - data.sys.sunset
                    val gg = data.sys.sunrise - data.dt

                    val jj = 100 * gg / kk

                    Log.d("dlksldsk", "${kk}")
                    Log.d("dlksldsk", "${gg}     l")
                    Log.d("dlksldsk", "${jj}     k")

                    if (jj > 100) {
                        binding.sunTime.progress = 100
                    } else if (jj < 0) {
                        binding.sunTime.progress = 1
                    } else {
                        binding.sunTime.progress = jj
                    }

                    binding.city.text = "${data.name}"

                    binding.wind.text =
                        "\uD83C\uDF2A${data.wind.speed.toInt() + 10} km/h\n  wind speed"
                    binding.humidity.text = " \uD83D\uDCA7${data.main.humidity}%\nHumidity"
                    binding.rain.text = "      ⛈ ${data.main.feelsLike.toInt()}%\nChance of rain"

                    binding.sunny.text = "${data.weather[0].description}"

                    val simpleDate = SimpleDateFormat("EEEE, dd MMM")
                    val currentDate = simpleDate.format(Date())
                    binding.date.text = "$currentDate"

                    when (data.weather[0].icon) {
                        "01d" -> {

                            binding.weatherIcon.setImageResource(R.drawable.nolbird)
                            binding.topLiner.setBackgroundResource(R.drawable.shapeday)
                            binding.main.setBackgroundResource(R.drawable.shapebasic)
                            binding.backLiner.setBackgroundResource(R.drawable.shapeshadow)
                        }

                        "01n" -> {
                            binding.weatherIcon.setImageResource(R.drawable.nolbirn)
                            binding.topLiner.setBackgroundResource(R.drawable.shapenight)
                            binding.main.setBackgroundResource(R.drawable.shapebasic2)
                            binding.backLiner.setBackgroundResource(R.drawable.shapeshadow2)
                        }

                        "02d" -> {
                            binding.weatherIcon.setImageResource(R.drawable.nolikkid)
                            binding.topLiner.setBackgroundResource(R.drawable.shapeday)
                            binding.main.setBackgroundResource(R.drawable.shapebasic)
                            binding.backLiner.setBackgroundResource(R.drawable.shapeshadow)
                        }

                        "02n" -> {
                            binding.weatherIcon.setImageResource(R.drawable.nolikkin)
                            binding.topLiner.setBackgroundResource(R.drawable.shapenight)
                            binding.main.setBackgroundResource(R.drawable.shapebasic2)
                            binding.backLiner.setBackgroundResource(R.drawable.shapeshadow2)
                        }

                        "03d" -> {
                            binding.weatherIcon.setImageResource(R.drawable.noluchd)
                            binding.topLiner.setBackgroundResource(R.drawable.shapeday)
                            binding.main.setBackgroundResource(R.drawable.shapebasic)
                            binding.backLiner.setBackgroundResource(R.drawable.shapeshadow)
                        }

                        "03n" -> {
                            binding.weatherIcon.setImageResource(R.drawable.noluchn)
                            binding.topLiner.setBackgroundResource(R.drawable.shapenight)
                            binding.main.setBackgroundResource(R.drawable.shapebasic2)
                            binding.backLiner.setBackgroundResource(R.drawable.shapeshadow2)
                        }

                        "04d" -> {
                            binding.weatherIcon.setImageResource(R.drawable.noltord)
                            binding.topLiner.setBackgroundResource(R.drawable.shapeday)
                            binding.main.setBackgroundResource(R.drawable.shapebasic)
                            binding.backLiner.setBackgroundResource(R.drawable.shapeshadow)
                        }


                        "04n" -> {
                            binding.weatherIcon.setImageResource(R.drawable.noltorn)
                            binding.topLiner.setBackgroundResource(R.drawable.shapeday)
                            binding.main.setBackgroundResource(R.drawable.shapebasic)
                            binding.backLiner.setBackgroundResource(R.drawable.shapeshadow)
                        }

                        "09d" -> {
                            binding.weatherIcon.setImageResource(R.drawable.noltoqizd)
                            binding.topLiner.setBackgroundResource(R.drawable.shapeday)
                            binding.main.setBackgroundResource(R.drawable.shapebasic)
                            binding.backLiner.setBackgroundResource(R.drawable.shapeshadow)
                        }
                        "09n" -> {
                            binding.weatherIcon.setImageResource(R.drawable.noltoqizn)
                            binding.topLiner.setBackgroundResource(R.drawable.shapeday)
                            binding.main.setBackgroundResource(R.drawable.shapebasic)
                            binding.backLiner.setBackgroundResource(R.drawable.shapeshadow)
                        }

                        "10d" -> {
                            binding.weatherIcon.setImageResource(R.drawable.ond)
                            binding.topLiner.setBackgroundResource(R.drawable.shapeday)
                            binding.main.setBackgroundResource(R.drawable.shapebasic)
                            binding.backLiner.setBackgroundResource(R.drawable.shapeshadow)
                        }

                        "10n" -> {
                            binding.weatherIcon.setImageResource(R.drawable.onn)
                            binding.topLiner.setBackgroundResource(R.drawable.shapeday)
                            binding.main.setBackgroundResource(R.drawable.shapebasic)
                            binding.backLiner.setBackgroundResource(R.drawable.shapeshadow)
                        }

                        "11d" -> {
                            binding.weatherIcon.setImageResource(R.drawable.onbird)
                            binding.topLiner.setBackgroundResource(R.drawable.shapeday)
                            binding.main.setBackgroundResource(R.drawable.shapebasic)
                            binding.backLiner.setBackgroundResource(R.drawable.shapeshadow)
                        }

                        "11n" -> {
                            binding.weatherIcon.setImageResource(R.drawable.onbirn)
                            binding.topLiner.setBackgroundResource(R.drawable.shapeday)
                            binding.main.setBackgroundResource(R.drawable.shapebasic)
                            binding.backLiner.setBackgroundResource(R.drawable.shapeshadow)
                        }

                        "13d" -> {
                            binding.weatherIcon.setImageResource(R.drawable.onuchd)
                            binding.topLiner.setBackgroundResource(R.drawable.shapeday)
                            binding.main.setBackgroundResource(R.drawable.shapebasic)
                            binding.backLiner.setBackgroundResource(R.drawable.shapeshadow)
                        }

                        "13n" -> {
                            binding.weatherIcon.setImageResource(R.drawable.onuchn)
                            binding.topLiner.setBackgroundResource(R.drawable.shapeday)
                            binding.main.setBackgroundResource(R.drawable.shapebasic)
                            binding.backLiner.setBackgroundResource(R.drawable.shapeshadow)
                        }

                        "50d" -> {
                            binding.weatherIcon.setImageResource(R.drawable.ellikd)
                            binding.topLiner.setBackgroundResource(R.drawable.shapeday)
                            binding.main.setBackgroundResource(R.drawable.shapebasic)
                            binding.backLiner.setBackgroundResource(R.drawable.shapeshadow)
                        }

                        "50n" -> {
                            binding.weatherIcon.setImageResource(R.drawable.ellikn)
                            binding.topLiner.setBackgroundResource(R.drawable.shapeday)
                            binding.main.setBackgroundResource(R.drawable.shapebasic)
                            binding.backLiner.setBackgroundResource(R.drawable.shapeshadow)
                        }
                        else -> {
                            binding.weatherIcon.setImageResource(R.drawable.unknown)
                            binding.topLiner.setBackgroundResource(R.drawable.shapeday)
                            binding.main.setBackgroundResource(R.drawable.shapebasic)
                            binding.backLiner.setBackgroundResource(R.drawable.shapeshadow)
                        }

                    }

                }
            }

            override fun onFailure(call: Call<CurrentWeather>, t: Throwable) {

            }
        })
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
                    }
                } else {
                }

            }

            override fun onFailure(call: Call<DailyResponse>, t: Throwable) {
            }

        })
    }

    private fun SimilarMoviesRecyclerView() {
        sliderAdapter = SliderHorizontalAdapter()
        binding.childList.adapter = sliderAdapter
        var layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.childList.layoutManager = layoutManager
    }

    private fun setSlider(dailyResponse: DailyResponse) {
        sliderAdapter!!.setData(dailyResponse.list)
    }

    private fun seekBar() {
        binding.sunTime.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(p0: View?, p1: MotionEvent?): Boolean {
                return true
            }

        })
    }

}


