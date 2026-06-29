package com.nmp.habittracker.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nmp.habittracker.Util.FileHelper
import com.nmp.habittracker.model.HabitDatabase
import com.nmp.habittracker.model.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class LoginViewModel(application: Application)
    : AndroidViewModel(application), CoroutineScope {

    val loginSuccessLD = MutableLiveData<Boolean>()
    val errorLD = MutableLiveData<String>()

    private val job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

    fun login(username:String,password:String){
        launch {
            val db = HabitDatabase.buildDatabase(getApplication())
            if (db.userDao().countUser() == 0) {
                db.userDao().insert(
                    User(
                        username = "student",
                        password = "123"
                    )
                )
            }

            val user = db.userDao().login(username,password)
            if(user != null){
                loginSuccessLD.postValue(true)
                val pref = FileHelper(getApplication())
                pref.saveLogin(username)
            }
            else{
                loginSuccessLD.postValue(false)
                errorLD.postValue("Username / Password salah")
            }
        }
    }
}