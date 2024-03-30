package com.example.house_rental_app.repository

import android.content.Context
import com.example.house_rental_app.dao.HouseDao
import com.example.house_rental_app.database.UserDatabase
import com.example.house_rental_app.entity.HouseEntity
import kotlinx.coroutines.flow.Flow

class HouseRepository(context: Context) {

    private val houseDao: HouseDao = UserDatabase.getDatabase(context).houseDao()

    suspend fun addHouse(house: HouseEntity) {
        houseDao.addHouse(house)
    }

    suspend fun deleteHouse(house: HouseEntity) {
        houseDao.deleteHouse(house)
    }

    fun viewAllHousesBasedOnOwnerID(userId: Int): Flow<List<HouseEntity>> {
        return houseDao.viewAllHousesBasedOnOwnerID(userId)
    }

    suspend fun editHouse(house: HouseEntity) {
        houseDao.editHouse(house)
    }

    // You can define other functions for house-related operations here
}