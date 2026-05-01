package com.nmp.habittracker.Model

data class Habit(
    val id: Int,
    val name: String,
    val description: String,
    val target: Int,
    var currentProgress: Int,
    var status: String = "In Progress"
)