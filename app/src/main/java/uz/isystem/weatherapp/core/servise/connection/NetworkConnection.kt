package uz.isystem.currency.network.servise.connection

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkConnection {
    companion object {
        private var retrofit: Retrofit? = null
        fun getRetrofit(): Retrofit {
//            http://api.openweathermap.org/data/2.5/forecast?id=524901&appid={API key}
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl("https://api.openweathermap.org/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit!!
        }
    }
}