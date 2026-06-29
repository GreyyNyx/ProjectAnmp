package com.nmp.habittracker.View

import android.view.View
import com.nmp.habittracker.model.Habit

interface HabitListener {
    fun onPlusClick(habit: Habit)
    fun onMinusClick(habit: Habit)
    fun onTitleClick(v: View)
}