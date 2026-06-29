package com.nmp.habittracker.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Habit(
    @ColumnInfo(name="name")
    val name: String,
    @ColumnInfo(name="description")
    val description: String,
    @ColumnInfo(name="goal")
    val goal: Int,
    @ColumnInfo(name="unit")
    val unit: String,
    @ColumnInfo(name="icon")
    val icon: String,
    @ColumnInfo(name="progress")
    var progress: Int = 0,
){
    @PrimaryKey(autoGenerate = true)
    var uuid:Int =0

    fun isCompleted(): Boolean {
        return progress >= goal
    }
}