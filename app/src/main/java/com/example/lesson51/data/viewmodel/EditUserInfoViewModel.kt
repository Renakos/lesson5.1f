package com.example.lesson51.data.viewmodel

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.lesson51.data.model.UserInfo

class EditUserInfoViewModel : ViewModel() {

    private val _userInfoLiveData = MutableLiveData<UserInfo>()
    val userInfoLiveData: LiveData<UserInfo> = _userInfoLiveData


    private var userInfo: UserInfo? = null

    fun initialize(savedUserInfo: UserInfo?) {
        userInfo = savedUserInfo
        loadUserInfo()
    }

    private fun loadUserInfo() {
        userInfo?.let {
            _userInfoLiveData.value = it
        }
    }

    fun saveChanges(
        username: String,
        age: Int,
        email: String,
        password: String,
        sharedPreferences: SharedPreferences
    ) {
        userInfo = UserInfo(username, age, email, password)

        userInfo?.let {
            sharedPreferences.edit().apply {
                putString("username", it.username)
                putInt("age", it.age)
                putString("email", it.email)
                putString("password", it.password)
                apply()
            }
        }
    }

}
