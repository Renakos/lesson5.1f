package com.example.lesson51

import android.app.Application
import com.example.lesson51.data.preferences.SharedPreferencesHelper

class App : Application()  {
    override fun onCreate() {
        super.onCreate()
        SharedPreferencesHelper.init(this)
    }
}