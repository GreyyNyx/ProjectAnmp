package com.nmp.habittracker.model

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.nmp.habittracker.Util.FileHelper

object HabitRepository {

    fun loadHabits(context: Context): ArrayList<Habit> {

        val fileHelper = FileHelper(context)

        val jsonString = fileHelper.readFromFileExternal()

        return if (!jsonString.isNullOrBlank()) {

            val sType = object : TypeToken<ArrayList<Habit>>() {}.type

            Gson().fromJson(jsonString, sType)

        } else {

            arrayListOf()
        }
    }

    fun saveHabits(
        context: Context,
        habits: ArrayList<Habit>
    ) {

        val fileHelper = FileHelper(context)

        val jsonString = Gson().toJson(habits)

        fileHelper.writeToFileExternal(jsonString)
    }

    fun addHabit(
        context: Context,
        habit: Habit
    ) {

        val habits = loadHabits(context)

        habits.add(habit)

        saveHabits(context, habits)
    }

    fun updateHabits(
        context: Context,
        habits: ArrayList<Habit>
    ) {

        saveHabits(context, habits)
    }
}