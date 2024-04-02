package com.example.house_rental_app

import android.content.Context
import com.example.house_rental_app.database.UserDatabase
import com.example.house_rental_app.repository.HouseRepository
import com.example.house_rental_app.repository.HouseRepositoryImpl
import com.example.house_rental_app.repository.OfflineUserRepository
import com.example.house_rental_app.repository.UserRepository

interface AppContainer {
    val userRepository: UserRepository
    val houseRepository: HouseRepository
}

class AppDataContainer(private val context: Context): AppContainer{

    override val userRepository: UserRepository by lazy {
        OfflineUserRepository(UserDatabase.getDatabase(context).userDao())
    }
    override val houseRepository: HouseRepository by lazy{
        HouseRepositoryImpl(UserDatabase.getDatabase(context).houseDao())
    }
}