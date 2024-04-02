package com.example.house_rental_app.repository

import com.example.house_rental_app.dao.UserDao
import com.example.house_rental_app.entity.UserEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface UserRepository {

    val userDao: UserDao

    suspend fun registerUser(user: UserEntity): Boolean = withContext(Dispatchers.IO) {
        try {
            userDao.registerUser(user)
            // Logic here to check if the user was successfully registered.
            // For now, assuming it's always successful, but you might want to actually check this based on your application's requirements.
            true // Automatically returned from withContext
        } catch (e: Exception) {
            // Log the error or handle it as needed
            false // Return false if an exception occurs
        }
    }

    suspend fun userLogin(emailId: String, password: String): Int{
        return withContext(Dispatchers.IO) {
            userDao.userLogin(emailId, password)
        }
    }
    suspend fun fetchUserById(userId: Int): UserEntity {
        return withContext(Dispatchers.IO) {
            userDao.fetchUserById(userId)
        }
    }
    suspend fun updateUser(user: UserEntity){
        withContext(Dispatchers.IO) {
            userDao.updateUser(user)
        }
    }
}