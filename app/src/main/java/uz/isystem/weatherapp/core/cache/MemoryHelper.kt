package uz.isystem.weatherapp.core.cache

import android.content.Context
import android.content.SharedPreferences

class MemoryHelper private constructor(context: Context) {
    private val preferences: SharedPreferences
    fun saveData(userData: UserData) {
        preferences.edit().putFloat("latitude", userData.latitude).apply()
        preferences.edit().putFloat("longitude", userData.longitude).apply()
        saveLastIndex()
    }

    fun saveLastIndex() {
        preferences.edit().putInt("index", lastIndex + 1).apply()
    }

    val lastIndex: Int
        get() = preferences.getInt("index", 0)

    fun getLatData(): UserData {
        return UserData(
            preferences.getFloat("latitude", 0f),
            preferences.getFloat("longitude", 0f),
        )
    }


    fun clearData() {
        preferences.edit().clear().apply()
    }

    companion object {
        var helper: MemoryHelper? = null
            private set

        fun init(context: Context) {
            if (helper == null) {
                helper = MemoryHelper(context)
            }
        }
    }

    init {
        // SharedPreferences
        preferences = context.getSharedPreferences("Weather_data", Context.MODE_PRIVATE)
    }
}