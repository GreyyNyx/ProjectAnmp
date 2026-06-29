package com.nmp.habittracker.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @ColumnInfo(name="username")
    val username:String,
    @ColumnInfo(name="password")
    val password:String
){
    @PrimaryKey(autoGenerate = true)
    var id:Int =0
}