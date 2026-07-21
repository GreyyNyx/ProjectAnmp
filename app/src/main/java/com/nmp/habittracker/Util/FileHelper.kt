package com.nmp.habittracker.Util

import android.content.Context
import android.os.Environment
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class FileHelper (context: Context) {

    private val SESSION_TIMEOUT = 30 * 1000L // 2 menit

    private val pref = context.getSharedPreferences(
        "habit_pref",
        Context.MODE_PRIVATE
    )

    fun saveLogin(username: String) {
        pref.edit()
            .putBoolean("isLogin", true)
            .putString("username", username)
            .putLong("loginTime", System.currentTimeMillis())
            .apply()
    }
    fun isLogin(): Boolean {
        return pref.getBoolean("isLogin", false)
    }

    fun getUsername(): String? {
        return pref.getString("username", "")
    }

    fun isSessionExpired(): Boolean {
        val loginTime = pref.getLong("loginTime", 0)
        if (loginTime == 0L) return true
        val currentTime = System.currentTimeMillis()
        return (currentTime - loginTime) > SESSION_TIMEOUT
    }

    fun checkSession(): Boolean {
        if (isSessionExpired()) {
            logout()
            return false
        }
        return isLogin()
    }

    fun logout() {
        pref.edit()
            .putBoolean("isLogin", false)
            .remove("username")
            .remove("loginTime")
            .apply()
    }

}