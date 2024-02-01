package com.example.lesson51

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.ViewModel

class RegistrationViewModel : ViewModel() {
    private lateinit var sharedPreferences: SharedPreferences

    fun initialize(context: Context) {
        sharedPreferences = context.getSharedPreferences("user_info", Context.MODE_PRIVATE)
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
