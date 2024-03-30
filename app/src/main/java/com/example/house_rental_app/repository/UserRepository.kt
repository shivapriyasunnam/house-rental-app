package com.example.house_rental_app.repository

import com.example.house_rental_app.dao.UserDao
import com.example.house_rental_app.entity.UserEntity
import com.example.house_rental_app.models.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface UserRepository {

    val userDao: UserDao

    suspend fun registerUser(user: UserEntity){
        withContext(Dispatchers.IO) {
            userDao.registerUser(user)
        }
    }

    suspend fun userLogin(emailId: String, password: String): UserEntity?{
        return withContext(Dispatchers.IO) {
            userDao.userLogin(emailId, password)
        }
    }

}