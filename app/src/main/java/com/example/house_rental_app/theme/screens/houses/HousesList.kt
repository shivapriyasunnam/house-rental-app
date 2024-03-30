package com.example.house_rental_app.theme.screens.houses

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.house_rental_app.entity.HouseEntity

@Composable
fun HousesList(houseList: List<HouseEntity>) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(houseList) { house ->
            HouseItem(house = house)
        }
    }
}
@Composable
fun HouseItem(house: HouseEntity) {
    // Composable for rendering each item in the RecyclerView
    // Customize this according to your UI design
}