package com.nmp.habittracker.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {
    @Insert
    fun insert(user: User)
    @Query("SELECT * FROM user WHERE username=:username AND password=:password LIMIT 1")
    fun login(username:String,password:String): User?

    @Query("SELECT * FROM user")
    fun getAllUser():List<User>
    @Query("SELECT COUNT(*) FROM user")
    fun countUser(): Int
}