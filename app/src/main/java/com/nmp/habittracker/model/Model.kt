package com.nmp.habittracker.model

data class Habit(
    val name: String,
    val description: String,
    val goal: Int,
    val unit: String,
    val icon: String,
    var progress: Int = 0,
){

    fun isCompleted(): Boolean {
        return progress >= goal
    }
}