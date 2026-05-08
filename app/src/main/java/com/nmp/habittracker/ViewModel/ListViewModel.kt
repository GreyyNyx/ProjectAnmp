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
        val jsonString = filehelper.readFromFileExternal()

        Log.d("FILE_PATH", filehelper.getFilePathExternal())
        Log.d("JSON_DATA", jsonString)

        if (!jsonString.isNullOrBlank()) {
            try {
                val sType = object : TypeToken<List<Habit>>() {}.type
                val result = Gson().fromJson<List<Habit>>(jsonString, sType)
                habitsLD.value = ArrayList(result)
                habitLoadErrorLD.value = false
            } catch (e: Exception) {
                e.printStackTrace()
                habitLoadErrorLD.value = true
            }
        } else {
            val dummyList = generateDummyData()
            saveHabits(dummyList)
            habitsLD.value = dummyList
            habitLoadErrorLD.value = false
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
    fun saveHabits(habits: ArrayList<Habit>) {
        val fileHelper = FileHelper(getApplication())
        val jsonString = Gson().toJson(habits)

        Log.d("WRITE_JSON", jsonString)
        Log.d("FILE_PATH", fileHelper.getFilePathExternal())

        fileHelper.writeToFileExternal(jsonString)
    }

    fun addHabit(habit: Habit) {

        val fileHelper = FileHelper(getApplication())
        val jsonString = fileHelper.readFromFileExternal()

        val existingList: ArrayList<Habit>

        if (!jsonString.isNullOrBlank()) {
            val sType = object : TypeToken<List<Habit>>() {}.type
            val result = Gson().fromJson<List<Habit>>(jsonString, sType)
            existingList = ArrayList(result)
        } else {

            existingList = arrayListOf()
        }
        existingList.add(habit)
        fileHelper.writeToFileExternal(Gson().toJson(existingList))
        habitsLD.value = existingList
    }
    fun updateHabits(habits: ArrayList<Habit>) {
        val fileHelper = FileHelper(getApplication())
        val jsonString = Gson().toJson(habits)
        fileHelper.writeToFileExternal(jsonString)
    }
}