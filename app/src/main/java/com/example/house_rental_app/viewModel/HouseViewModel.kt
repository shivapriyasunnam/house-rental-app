package com.example.house_rental_app.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.house_rental_app.entity.HouseEntity
import com.example.house_rental_app.repository.HouseRepository
import kotlinx.coroutines.launch

class HouseViewModel(context: Context) : ViewModel() {

    private val houseRepository = HouseRepository(context)

    fun addHouse(house: HouseEntity) {
        viewModelScope.launch {
            houseRepository.addHouse(house)
        }
    }

    fun deleteHouse(house: HouseEntity) {
        viewModelScope.launch {
            houseRepository.deleteHouse(house)
        }
    }

    fun viewAllHousesBasedOnOwnerID(userId: Int) {
        houseRepository.viewAllHousesBasedOnOwnerID(userId)
    }

    fun editHouse(house: HouseEntity) {
        viewModelScope.launch {
            houseRepository.editHouse(house)
        }
    }
}