package com.example.offlinefirstapp

import android.app.Application
import com.example.offlinefirstapp.data.RetrofitInstance
import com.example.offlinefirstapp.data.UserDatabase
import com.example.offlinefirstapp.data.UserRepository


class UserApplication : Application() {
    private val database by lazy {
        UserDatabase.getDatabase(this)
    }
    val repository by lazy {
        UserRepository(
            RetrofitInstance.api,
            database.userDao()
        )
    }
}