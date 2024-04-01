package com.example.house_rental_app.repository

import android.content.Context
import com.example.house_rental_app.dao.HouseDao
import com.example.house_rental_app.database.UserDatabase
import com.example.house_rental_app.entity.HouseEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

interface HouseRepository {

    val houseDao: HouseDao

    suspend fun addHouse(house: HouseEntity) {
        withContext(Dispatchers.IO){
            houseDao.addHouse(house)
        }
    }

    suspend fun deleteHouse(house: HouseEntity) {
        withContext(Dispatchers.IO){
            houseDao.deleteHouse(house)
        }
    }

    suspend fun viewAllHousesBasedOnOwnerID(userId: Int): Flow<List<HouseEntity>> {
        return withContext(Dispatchers.IO){
            houseDao.viewAllHousesBasedOnOwnerID(userId)
        }
    }

    suspend fun editHouse(house: HouseEntity) {
        withContext(Dispatchers.IO){
            houseDao.editHouse(house)
        }
    }
    suspend fun viewAllHouses(): Flow<List<HouseEntity>>{
        return withContext(Dispatchers.IO){
            houseDao.viewAllHouses()
        }
    }

    // You can define other functions for house-related operations here
}