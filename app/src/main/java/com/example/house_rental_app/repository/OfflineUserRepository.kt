package com.example.house_rental_app.repository

import com.example.house_rental_app.dao.UserDao
import com.example.house_rental_app.entity.UserEntity
import com.example.house_rental_app.models.User

class OfflineUserRepository(override val userDao: UserDao): UserRepository{
    override suspend fun registerUser(user: UserEntity) = userDao.registerUser(user)


    override suspend fun userLogin(emailId: String, password: String): Int = userDao.userLogin(emailId, password)
    override suspend fun updateUser(user: UserEntity) {
        userDao.updateUser(user)
    }
}