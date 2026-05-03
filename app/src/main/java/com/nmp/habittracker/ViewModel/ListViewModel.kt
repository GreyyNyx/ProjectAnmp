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

        saveDummyToFile()

        val jsonString = filehelper.readFromFileExternal()
        Log.d("FILE_PATH", filehelper.getFilePathExternal())
        Log.d("JSON_DATA", jsonString)

        if (!jsonString.isNullOrBlank()) {
            try {
                val sType = object : TypeToken<List<Habit>>() {}.type
                val result = Gson().fromJson<List<Habit>>(jsonString, sType)

                habitsLD.value = ArrayList(result)

            } catch (e: Exception) {
                e.printStackTrace()
                habitLoadErrorLD.value = true
            }
        } else {
            habitLoadErrorLD.value = true
        }
        loadingLD.value = false
    }
    fun generateDummyData(): ArrayList<Habit> {
        return arrayListOf(
            Habit(
                "Drink Water",
                "Stay hydrated throughout the day",
                8,
                "glasses",
                "glass_of_water",
                3,
                false
            ),
            Habit(
                "Exercise",
                "Daily workout routine",
                30,
                "minutes",
                "exercise",
                15,
                false
            ),
            Habit(
                "Read Books",
                "Expand your knowledge",
                20,
                "pages",
                "book",
                20,
                true
            ),
            Habit(
                "Meditation",
                "Mindfulness practice",
                10,
                "minutes",
                "yoga",
                0,
                false
            )
        )
    }
    fun saveDummyToFile() {
        val fileHelper = FileHelper(getApplication())

        val dummyList = generateDummyData()

        val jsonString = Gson().toJson(dummyList)

        Log.d("WRITE_JSON", jsonString)
        Log.d("FILE_PATH", fileHelper.getFilePathExternal())

        fileHelper.writeToFileExternal(jsonString)
    }
}