package uz.isystem.weatherapp.core.app

import android.app.Application
import uz.isystem.weatherapp.core.cache.MemoryHelper

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        MemoryHelper.init(this)
    }

}