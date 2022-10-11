package uz.isystem.weatherapp.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import uz.isystem.weatherapp.R
import uz.isystem.weatherapp.core.adapter.OnBoardAdapter
import uz.isystem.weatherapp.core.moduls.BoardData
import uz.isystem.weatherapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var data: ArrayList<BoardData>

    private val adapter = OnBoardAdapter()

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        editFavoriteDialog?.show(supportFragmentManager, "FavoriteEditDialog")
//        editFavoriteDialog?.show(childFragmentManager, "FavoriteEditDialog")
//        binding.onBoardPager.setPageTransformer
        binding.onBoardPager.adapter = adapter
        binding.wormDotsIndicator.attachTo(binding.onBoardPager)

        binding.onBoardPager.apply {
            orientation = ViewPager2.ORIENTATION_HORIZONTAL
            (getChildAt(0) as RecyclerView).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        }

        loadBoardData()

        binding.skipButton.setOnClickListener {
            if (binding.onBoardPager.currentItem == 2) {
                val intent = Intent(this@MainActivity, WeatherActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                binding.onBoardPager.setCurrentItem(binding.onBoardPager.currentItem + 1, true)
            }
        }

    }

//    override fun onResume() {
//        super.onResume()
//        binding.onBoardPager.registerOnPageChangeCallback(pagerCallback)
//    }

    private val pagerCallback = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)

            if (position == 2) {
                binding.skipButton.setText(R.string.start)
            } else {
                binding.skipButton.setText(R.string.next)
            }

        }
    }

    override fun onStop() {
        super.onStop()
        binding.onBoardPager.unregisterOnPageChangeCallback(pagerCallback)
    }

    private fun loadBoardData() {
        this.data = ArrayList<BoardData>()
        data.add(
            BoardData(
                title = "Latest and more information",
                description = "With us you can always preview information for the last and five days",
                image = R.drawable.page_1,
                backgroundColor = getColor(R.color.myWhite)
            )
        )
        data.add(
            BoardData(
                title = "Weather in the world",
                description = "With us, you can see the weather in any part of the world at the same time",
                image = R.drawable.page_2,
                backgroundColor = getColor(R.color.myWhite)
            )
        )
        data.add(
            BoardData(
                title = "Easy and convenient search",
                description = "You will be able to choose the city you want easily and comfortably",
                image = R.drawable.page_3,
                backgroundColor = getColor(R.color.myWhite)
            )
        )
        adapter.data = data
    }
}