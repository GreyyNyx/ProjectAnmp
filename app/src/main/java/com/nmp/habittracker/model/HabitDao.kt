package com.nmp.habittracker.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface HabitDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg habit: Habit)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(habit: Habit)

    @Query("SELECT * FROM habit")
    fun selectAllHabit(): List<Habit>

    @Query("SELECT * FROM habit WHERE uuid= :id")
    fun selectHabit(id:Int): Habit

    @Delete
    fun deleteHabit(habit: Habit)

    @Query("UPDATE habit SET name=:name, description=:description, goal=:goal, unit=:unit, icon=:icon, progress=:progress WHERE uuid=:id")
    fun update(name: String, description: String, goal: Int, unit: String, icon: String, progress: Int, id: Int)

    @Update
    fun updateHabit(habit: Habit)
}