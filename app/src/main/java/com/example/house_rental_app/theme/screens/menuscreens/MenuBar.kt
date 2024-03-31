package com.example.house_rental_app.theme.screens.menuscreens


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.house_rental_app.R
import com.example.house_rental_app.data.PropertyDetails
import com.example.house_rental_app.navigation.ROUTE_ADD_PROPERTY
import com.example.house_rental_app.navigation.ROUTE_ALL_LISTINGS
import com.example.house_rental_app.navigation.ROUTE_MY_LISTINGS
import com.example.house_rental_app.navigation.ROUTE_USER_PROFILE

@Composable
fun MenuBar(navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(onClick = { navController.navigate(ROUTE_ALL_LISTINGS) }) {
            Icon(Icons.Default.List, contentDescription = "Listings")
        }

        // View all listings icon
        IconButton(onClick = { navController.navigate(ROUTE_MY_LISTINGS) }) {
            Icon(Icons.Default.Search, contentDescription = "My Listing")
        }

        // Profile icon
        IconButton(onClick = { navController.navigate(ROUTE_USER_PROFILE)}) {
            Icon(Icons.Default.Person, contentDescription = "Profile")
        }

        // Add listing icon
        IconButton(onClick = { navController.navigate(ROUTE_ADD_PROPERTY) }) {
            Icon(Icons.Default.Add, contentDescription = "Add Listing")
        }
    }
}

