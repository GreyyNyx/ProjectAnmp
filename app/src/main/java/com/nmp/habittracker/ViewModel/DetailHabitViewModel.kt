package com.nmp.habittracker.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.nmp.habittracker.model.Habit
import com.nmp.habittracker.model.HabitDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class DetailHabitViewModel(application: Application)
    : AndroidViewModel(application), CoroutineScope {
    private val job = Job()

    val habitLD = MutableLiveData<Habit>()

    fun fetch(uuid:Int) {
        launch {
            val db = HabitDatabase.buildDatabase(getApplication())
            habitLD.postValue(db.habitDao().selectHabit(uuid))
        }
    }

    fun updateHabit(habit: Habit) {
        launch {
            val db = HabitDatabase.buildDatabase(
                getApplication()
            )
            db.habitDao().updateHabit(habit)
        }
    }
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO
}