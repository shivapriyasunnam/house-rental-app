package com.example.house_rental_app.repository

import com.example.house_rental_app.dao.HouseDao
import com.example.house_rental_app.entity.HouseEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

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

    override suspend fun viewAllHouses(): Flow<List<HouseEntity>> {
        return super.viewAllHouses()
    }

    override suspend fun viewAllHousesBasedOnOwnerID(userId: Int): Flow<List<HouseEntity>> {
        return super.viewAllHousesBasedOnOwnerID(userId)
    }
    override suspend fun getHouseById(houseId: Int): HouseEntity {
        return withContext(Dispatchers.IO) {
            houseDao.getHouseById(houseId)
        }
    }
    
}