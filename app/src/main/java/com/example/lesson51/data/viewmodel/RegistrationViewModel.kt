package com.example.lesson51.data.viewmodel

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel

class RegistrationViewModel : ViewModel() {
    private lateinit var sharedPreferences: SharedPreferences

    fun initialize(sharedPreferences: SharedPreferences) {
        this.sharedPreferences = sharedPreferences
    }

    fun registerUser(username: String, age: Int, email: String, password: String) {
        sharedPreferences.edit().apply {
            putString("username", username)
            putInt("age", age)
            putString("email", email)
            putString("password", password)
            apply()
        }
    }
}
