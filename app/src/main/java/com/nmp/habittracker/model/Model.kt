package com.nmp.habittracker.model

data class Habit(
    val name: String,
    val description: String,
    val goal: Int,
    val unit: String,
    val icon: String,
    val progress: Int = 0,
    val isCompleted: Boolean = false
)