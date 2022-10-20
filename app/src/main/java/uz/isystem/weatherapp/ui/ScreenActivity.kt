package uz.isystem.weatherapp.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import uz.isystem.weatherapp.R
import uz.isystem.weatherapp.databinding.ActivityScreenBinding

class ScreenActivity : AppCompatActivity() {

    private var _binding: ActivityScreenBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val rotationTop1: Animation = AnimationUtils.loadAnimation(
            applicationContext,
            R.anim.translate1
        )
        rotationTop1.isInitialized
        binding.sunSplash.startAnimation(rotationTop1)

        val rotationTop2: Animation = AnimationUtils.loadAnimation(
            applicationContext,
            R.anim.translate2
        )
        rotationTop2.isInitialized
        binding.cloudSplash1.startAnimation(rotationTop2)

        val rotationTop3: Animation = AnimationUtils.loadAnimation(
            applicationContext,
            R.anim.translate3
        )
        rotationTop3.isInitialized
        binding.cloudSplash2.startAnimation(rotationTop3)

        Handler().postDelayed({
            val intent = Intent(this, WeatherActivity::class.java)
            startActivity(intent)
            finish()
        }, 1450)
    }
}

