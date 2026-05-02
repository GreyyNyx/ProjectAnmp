package com.nmp.habittracker.ViewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.nmp.habittracker.Util.FileHelper
import com.nmp.habittracker.model.Habit


class ListViewModel (application: Application): AndroidViewModel(application){
    val habitsLD = MutableLiveData<ArrayList<Habit>>()
    val habitLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()
    fun refresh() {
        loadingLD.value = true
        habitLoadErrorLD.value = false

        val filehelper = FileHelper(getApplication())

        val jsonString = filehelper.readFromFile()

        if (jsonString.isNotEmpty()) {

            val sType = object : TypeToken<List<Habit>>() {}.type
            val result = Gson().fromJson<List<Habit>>(jsonString, sType)
            habitsLD.value = ArrayList(result)
            Log.d("print_file_read", result.toString())

        } else {
            habitLoadErrorLD.value = true
        }

        loadingLD.value = false
    }
}