package com.example.offlinefirstapp.data

import android.util.Log
import kotlinx.coroutines.flow.Flow

class UserRepository(
    private val apiService: ApiService,
    private val userDao: UserDao
) {

    val allUsers: Flow<List<User>> = userDao.getAllUsers()

    fun searchUsers(query: String): Flow<List<User>> {
        return userDao.searchUsers(query)
    }

    suspend fun refreshUsers() {
        try {
            val freshUsers = apiService.getUsers()
            userDao.insertUsers(freshUsers)

        } catch (e: Exception) {
            Log.e("UserRepository", "Failed to fetch users: ${e.message}")
        }
    }
}