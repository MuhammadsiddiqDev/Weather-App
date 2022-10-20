package uz.isystem.weatherapp.core.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.isystem.weatherapp.R
import uz.isystem.weatherapp.core.moduls.weather.hourlyModul.DataResponse
import uz.isystem.weatherapp.databinding.Item5DaysBinding
import java.text.SimpleDateFormat
import java.util.*


class SliderVerticalAdapter : RecyclerView.Adapter<SliderVerticalAdapter.ViewHolder>() {

    private val data = ArrayList<DataResponse>()


    fun setData(data: List<DataResponse>) {
        this.data.addAll(data)
        notifyItemRangeInserted(this.data.size - data.size, data.size)
    }


    inner class ViewHolder(private val binding: Item5DaysBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun binData(dataResponse: DataResponse) {

            val simpleDate = SimpleDateFormat("EEE")
            val currentDate = simpleDate.format(Date())

//            time management
            val date3 = Date(dataResponse.dt.toLong() * 1000)
            val simpleDate1 = SimpleDateFormat("EEE  HH:MM")
            val time = simpleDate1.format(date3)

            binding.sunny.text = "${dataResponse.weather[0].description}"
            binding.monday.text = "$time"
            binding.temp.text = "${dataResponse.main.tempMax.toInt()}"
            binding.temp2.text = "${dataResponse.main.tempMin.toInt()}"


            when (dataResponse.weather[0].icon) {
                "01d" -> binding.iconImage.setImageResource(R.drawable.nolbird)
                "01n" -> binding.iconImage.setImageResource(R.drawable.nolbirn)
                "02d" -> binding.iconImage.setImageResource(R.drawable.nolikkid)
                "02n" -> binding.iconImage.setImageResource(R.drawable.nolikkin)
                "03d" -> binding.iconImage.setImageResource(R.drawable.noluchd)
                "03n" -> binding.iconImage.setImageResource(R.drawable.noluchn)
                "04d" -> binding.iconImage.setImageResource(R.drawable.noltord)
                "04n" -> binding.iconImage.setImageResource(R.drawable.noltorn)
                "09d" -> binding.iconImage.setImageResource(R.drawable.noltoqizd)
                "09n" -> binding.iconImage.setImageResource(R.drawable.noltoqizn)
                "10d" -> binding.iconImage.setImageResource(R.drawable.ond)
                "10n" -> binding.iconImage.setImageResource(R.drawable.onn)
                "11d" -> binding.iconImage.setImageResource(R.drawable.onbird)
                "11n" -> binding.iconImage.setImageResource(R.drawable.onbirn)
                "13d" -> binding.iconImage.setImageResource(R.drawable.onuchd)
                "13n" -> binding.iconImage.setImageResource(R.drawable.onuchn)
                "50d" -> binding.iconImage.setImageResource(R.drawable.ellikd)
                "50n" -> binding.iconImage.setImageResource(R.drawable.ellikn)
                else -> binding.iconImage.setImageResource(R.drawable.unknown)
            }


        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            Item5DaysBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binData(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }


}