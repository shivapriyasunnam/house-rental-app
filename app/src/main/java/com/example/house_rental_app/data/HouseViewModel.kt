package com.example.house_rental_app.data

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.house_rental_app.entity.HouseEntity
import com.example.house_rental_app.repository.HouseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class HouseViewModel(context: Context) : ViewModel() {

    private val houseRepository = HouseRepository(context)

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
    fun viewAllHousesBasedOnOwnerID(userId: Int): Flow<List<HouseEntity>> {
        return houseRepository.viewAllHousesBasedOnOwnerID(userId)
    }

    // Function to edit house details
    fun editHouse(house: HouseEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            houseRepository.editHouse(house)
        }
    }
}