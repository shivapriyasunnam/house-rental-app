package com.example.house_rental_app.data

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.house_rental_app.DatabaseApplication
import com.example.house_rental_app.entity.HouseEntity
import com.example.house_rental_app.repository.HouseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch

class HouseViewModel() : ViewModel() {

    private val houseRepository : HouseRepository by lazy{
        DatabaseApplication.container.houseRepository
    }

    // Function to add a new house
    fun addHouse(house: HouseEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            houseRepository.addHouse(house)
        }
    }

    // Function to delete a house
    fun deleteHouse(house: HouseEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            houseRepository.deleteHouse(house)
        }
    }

    // Function to retrieve all houses based on owner ID
    suspend fun viewAllHousesBasedOnOwnerID(userId: Int): Flow<List<HouseEntity>> {
        var allHouses = emptyFlow<List<HouseEntity>>()
        viewModelScope.launch(Dispatchers.IO) {
             allHouses = houseRepository.viewAllHousesBasedOnOwnerID(userId)
        }
        return allHouses
    }

    // Function to edit house details
    fun editHouse(house: HouseEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            houseRepository.editHouse(house)
        }
    }
}