package com.example.house_rental_app.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.house_rental_app.entity.HouseEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface HouseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addHouse(house: HouseEntity)

    @Delete
    suspend fun deleteHouse(house: HouseEntity)

    @Query("SELECT * FROM house_table WHERE ownerId = :userId")
    fun viewAllHousesBasedOnOwnerID(userId: Int): Flow<List<HouseEntity>>

    @Update
    suspend fun editHouse(house: HouseEntity)

    @Query("SELECT * FROM house_table")
    fun viewAllHouses(): Flow<List<HouseEntity>>
}