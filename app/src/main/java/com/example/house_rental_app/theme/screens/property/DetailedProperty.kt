package com.example.house_rental_app.theme.screens.property

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.house_rental_app.R
import androidx.navigation.compose.rememberNavController
import com.example.house_rental_app.data.PropertyDetails

@Composable
fun DetailedProperty(navController: NavController, propertyDetails: PropertyDetails) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Show property image
        Image(
            painter = painterResource(id = propertyDetails.imageId),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        )

        // Show property details
        Text(text = "Address: ${propertyDetails.address}")
        Text(text = "Lease Availability: ${propertyDetails.leaseAvailability}")
        Text(text = "Bedrooms: ${propertyDetails.bedrooms}")
        Text(text = "Bathrooms: ${propertyDetails.bathrooms}")

        // Button to contact landlord
        Button(onClick = { /* Handle contact landlord button click */ }) {
            Text("Contact Landlord")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailedPropertyPreview() {
    val navController = rememberNavController()
    DetailedProperty(navController = navController, PropertyDetails(address = "456 Elm St", leaseAvailability = "May 1, 2024", imageId = R.drawable.img_1,  bedrooms = 2, bathrooms = 2))
}
