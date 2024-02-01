package com.example.lesson51

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UserInfoViewModel : ViewModel() {
    private val _userInfoLiveData = MutableLiveData<UserInfo>()
    val userInfoLiveData: LiveData<UserInfo> = _userInfoLiveData

    fun loadUserInfo(context: Context) {
        val sharedPreferences =
            context.getSharedPreferences("user_info", Context.MODE_PRIVATE)

        val username = sharedPreferences.getString("username", "") ?: ""
        val age = sharedPreferences.getInt("age", 0)
        val email = sharedPreferences.getString("email", "") ?: ""
        val password = sharedPreferences.getString("password", "") ?: ""

        val userInfo = UserInfo(username, age, email, password)
        _userInfoLiveData.value = userInfo
    }
}
