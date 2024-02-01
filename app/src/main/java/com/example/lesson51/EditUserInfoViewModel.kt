package com.example.lesson51

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class EditUserInfoViewModel : ViewModel() {
    private lateinit var sharedPreferences: SharedPreferences

    private val _userInfoLiveData = MutableLiveData<UserInfo>()
    val userInfoLiveData: LiveData<UserInfo> = _userInfoLiveData

    fun initialize(context: Context) {
        sharedPreferences = context.getSharedPreferences("user_info", Context.MODE_PRIVATE)
    }

    fun loadUserInfo() {
        val username = sharedPreferences.getString("username", "") ?: ""
        val age = sharedPreferences.getInt("age", 0)
        val email = sharedPreferences.getString("email", "") ?: ""
        val password = sharedPreferences.getString("password", "") ?: ""

        val userInfo = UserInfo(username, age, email, password)
        _userInfoLiveData.value = userInfo
    }

    fun saveChanges(username: String, age: Int, email: String, password: String) {
        sharedPreferences.edit().apply {
            putString("username", username)
            putInt("age", age)
            putString("email", email)
            putString("password", password)
            apply()
        }
    }
}
