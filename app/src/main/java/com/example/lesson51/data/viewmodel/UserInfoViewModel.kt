package com.example.lesson51.data.viewmodel

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.lesson51.data.model.UserInfo

class UserInfoViewModel : ViewModel() {
    private val _userInfoLiveData = MutableLiveData<UserInfo>()
    val userInfoLiveData: LiveData<UserInfo> = _userInfoLiveData

    fun loadUserInfo(sharedPreferences: SharedPreferences) {
        val username = sharedPreferences.getString("username", "") ?: ""
        val age = sharedPreferences.getInt("age", 0)
        val email = sharedPreferences.getString("email", "") ?: ""
        val password = sharedPreferences.getString("password", "") ?: ""

        val userInfo = UserInfo(username, age, email, password)
        _userInfoLiveData.value = userInfo
    }
}
