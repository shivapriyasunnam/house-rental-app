package com.example.house_rental_app.repository

import com.example.house_rental_app.dao.UserDao
import com.example.house_rental_app.entity.UserEntity

class OfflineUserRepository(override val userDao: UserDao): UserRepository{
    override suspend fun registerUser(user: UserEntity): Boolean {
        return try {
            userDao.registerUser(user)
            // Assuming the operation is successful if no exception is thrown
            true
        } catch (e: Exception) {
            // Log the error or handle it as needed
            false // Return false if an exception occurs, indicating failure
        }
    }


    override suspend fun userLogin(emailId: String, password: String): Int = userDao.userLogin(emailId, password)
    override suspend fun updateUser(user: UserEntity) {
        userDao.updateUser(user)
    }
}