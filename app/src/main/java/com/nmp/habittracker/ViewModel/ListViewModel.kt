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


class ListViewModel (application: Application): AndroidViewModel(application), CoroutineScope{
    val habitsLD = MutableLiveData<List<Habit>>()
    val habitLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()
    private var job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO
    fun refresh() {
        loadingLD.value = true
        habitLoadErrorLD.value = false

        launch {
            val db = HabitDatabase.buildDatabase(
                getApplication()
            )
            if (db.habitDao().selectAllHabit().isEmpty()) {
                db.habitDao().insertAll(
                    *generateDummyData().toTypedArray()
                )
            }

            habitsLD.postValue(db.habitDao().selectAllHabit())
            loadingLD.postValue(false)
        }
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
            ),
            Habit(
                "Exercise",
                "Daily workout routine",
                30,
                "minutes",
                "exercise",
                15,
            ),
            Habit(
                "Read Books",
                "Expand your knowledge",
                20,
                "pages",
                "book",
                20,
            ),
            Habit(
                "Meditation",
                "Mindfulness practice",
                10,
                "minutes",
                "yoga",
                0,
            )
        )
    }
    fun addHabit(habit: Habit) {
        launch {
            val db = HabitDatabase.buildDatabase(
                getApplication()
            )
            db.habitDao().insert(habit)
            habitsLD.postValue(db.habitDao().selectAllHabit())
        }
    }
    fun updateHabit(habit: Habit) {
        launch {
            val db = HabitDatabase.buildDatabase(
                getApplication()
            )
            db.habitDao().updateHabit(habit)
            habitsLD.postValue(db.habitDao().selectAllHabit())
        }
    }

    fun clearHabit(habit: Habit) {
        launch {
            val db = HabitDatabase.buildDatabase(
                getApplication()
            )
            db.habitDao().deleteHabit(habit)
            habitsLD.postValue(db.habitDao().selectAllHabit())
        }
    }
}