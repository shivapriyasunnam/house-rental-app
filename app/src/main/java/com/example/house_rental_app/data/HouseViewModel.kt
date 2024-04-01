package com.example.house_rental_app.data

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.house_rental_app.DatabaseApplication
import com.example.house_rental_app.entity.HouseEntity
import com.example.house_rental_app.repository.HouseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class HouseViewModel() : ViewModel() {
    private val _allHouses = MutableLiveData<List<HouseEntity>>()
    val allHouses: LiveData<List<HouseEntity>> get() = _allHouses

    private val houseRepository : HouseRepository by lazy{
        DatabaseApplication.container.houseRepository
    }
    init {
        viewModelScope.launch(Dispatchers.IO) {
            houseRepository.viewAllHouses().collect { housesList ->
                _allHouses.postValue(housesList)
            }
        }
    }
    // Function to add a new house
    suspend fun addHouse(house: HouseEntity) {
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
    fun viewHousesByOwnerId(userId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            houseRepository.viewAllHousesBasedOnOwnerID(userId).collect { housesList ->
                _allHouses.postValue(housesList)
            }
        }
    }

    // Function to edit house details
    fun editHouse(house: HouseEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            houseRepository.editHouse(house)
        }
    }
//    fun viewAllHouses(): Flow<List<HouseEntity>>{
//        val houses = emptyFlow<List<HouseEntity>>()
//        viewModelScope.launch(Dispatchers.IO){
//            houseRepository.viewAllHouses()
//        }
//        return houses
//    }
}