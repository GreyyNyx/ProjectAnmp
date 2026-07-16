package com.nmp.habittracker.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Habit(
    @ColumnInfo(name="name")
    var name: String,
    @ColumnInfo(name="description")
    var description: String,
    @ColumnInfo(name="goal")
    var goal: Int,
    @ColumnInfo(name="unit")
    var unit: String,
    @ColumnInfo(name="icon")
    var icon: String,
    @ColumnInfo(name="progress")
    var progress: Int = 0,
){
    @PrimaryKey(autoGenerate = true)
    var uuid:Int =0

    fun isCompleted(): Boolean {
        return progress >= goal
    }
}