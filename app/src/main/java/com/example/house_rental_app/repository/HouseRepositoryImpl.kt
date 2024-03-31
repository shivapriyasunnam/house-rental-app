package com.example.house_rental_app.repository

import com.example.house_rental_app.dao.HouseDao
import com.example.house_rental_app.entity.HouseEntity
import kotlinx.coroutines.flow.Flow

class HouseRepositoryImpl(override val houseDao: HouseDao): HouseRepository {

    override suspend fun addHouse(house: HouseEntity) {
        super.addHouse(house)
    }

    override suspend fun deleteHouse(house: HouseEntity) {
        super.deleteHouse(house)
    }

    override suspend fun editHouse(house: HouseEntity) {
        super.editHouse(house)
    }

    override suspend fun viewAllHousesBasedOnOwnerID(userId: Int): Flow<List<HouseEntity>> {
        return super.viewAllHousesBasedOnOwnerID(userId)
    }
    
}