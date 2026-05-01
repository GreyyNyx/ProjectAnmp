package com.nmp.habittracker.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel: ViewModel() {
    val loginSuccessLD = MutableLiveData<Boolean>()
    val errorLD = MutableLiveData<String>()

    fun login(username: String, password: String) {
        if (username == "student" && password == "123") {
            loginSuccessLD.value = true
        } else {
            errorLD.value = "Username atau password salah!"
        }
    }
}