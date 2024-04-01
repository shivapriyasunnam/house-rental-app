package com.example.house_rental_app.theme.screens.property

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.house_rental_app.entity.HouseEntity
import com.example.house_rental_app.navigation.ROUTE_CONTACT_LANDLORD

@Composable
fun DetailedProperty(navController: NavController, houseEntity: HouseEntity) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Show property image
        Image(
            painter = painterResource(id = houseEntity.images.toInt()),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        )

        // Show property details
        Text(text = "Address: ${houseEntity.address}")
        Text(text = "Lease Availability: ${houseEntity.lease}")
        Text(text = "Price: ${houseEntity.price}")
        Text(text = "Description: ${houseEntity.description}")

        // Button to contact landlord
        Button(onClick = { navController.navigate(ROUTE_CONTACT_LANDLORD) }) {
            Text("Contact Landlord")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailedPropertyPreview() {
    val navController = rememberNavController()
    DetailedProperty(navController = navController,  HouseEntity( address = "111 Main St", price = 234, ownerId = 23, lease = "April 1st 2022", images = "img_2", description = "A 2 bedroom" ))
}
