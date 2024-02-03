package com.example.lesson51.data.preferences

import android.app.Application
import android.content.SharedPreferences
import android.preference.PreferenceManager

object SharedPreferencesHelper {
    private lateinit var sharedPreferences: SharedPreferences

    fun init(application: Application) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(application)
    }

    fun saveString(key: String, value: String) {
        val editor = sharedPreferences.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun getString(key: String, defaultValue: String): String {
        return sharedPreferences.getString(key, defaultValue) ?: defaultValue
    }

    fun saveInt(key: String, value: Int) {
        val editor = sharedPreferences.edit()
        editor.putInt(key, value)
        editor.apply()
    }

    fun getInt(key: String, defaultValue: Int): Int {
        return sharedPreferences.getInt(key, defaultValue)
    }
}
