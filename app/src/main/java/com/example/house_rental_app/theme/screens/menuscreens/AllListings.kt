package com.example.house_rental_app.theme.screens.menuscreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.house_rental_app.R
import com.example.house_rental_app.data.PropertyDetails
import com.example.house_rental_app.navigation.ROUTE_ALL_LISTINGS
import com.example.house_rental_app.navigation.ROUTE_DETAILED_PROPERTY
import com.example.house_rental_app.navigation.ROUTE_MY_LISTINGS

@Composable
fun AllListings(navController: NavHostController) {

    Column {
        // Menu bar
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
            IconButton(onClick = {  navController.navigate(ROUTE_MY_LISTINGS) }) {
                Icon(Icons.Default.Search, contentDescription = "My Listing")
            }

            // Profile icon
            IconButton(onClick = { /* Handle profile icon click */ }) {
                Icon(Icons.Default.Person, contentDescription = "Profile")
            }

            // Add listing icon
            IconButton(onClick = { /* Handle add listing icon click */ }) {
                Icon(Icons.Default.Add, contentDescription = "Add Listing")
            }

        }

        // Spacer to add some space between menu bar and list
        Spacer(modifier = Modifier.height(1.dp))

        @Composable
        fun ImageListItem(
            modifier: Modifier = Modifier,
            imageId: Int,
            address: String,
            leaseAvailability: String,
            bedrooms: Int,
            bathrooms: Int,
            onClick: () -> Unit
        ) {
            val painter: Painter = painterResource(id = imageId)

            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .clickable(onClick = onClick), // Making the entire Column clickable
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painter,
                    contentDescription = null,
                    modifier = Modifier
                        .height(200.dp)
                        .fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(10.dp))
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Address: $address",
                        textAlign = TextAlign.Center // Center-aligning the text
                    )
                    Text(
                        text = "Lease Available From: $leaseAvailability",
                        textAlign = TextAlign.Center // Center-aligning the text
                    )
                    Text(
                        text = "Bedrooms: $bedrooms",
                        textAlign = TextAlign.Center // Center-aligning the text
                    )
                    Text(
                        text = "Bathrooms: $bathrooms",
                        textAlign = TextAlign.Center // Center-aligning the text
                    )
                }
            }
        }


        @Composable
        fun ScrollableListWithImages(
            imageList: List<PropertyDetails>,
            navController: NavController
        ) {
            LazyColumn {
                items(imageList) { propertyDetails ->
                    ImageListItem(
                        imageId = propertyDetails.imageId,
                        address = propertyDetails.address,
                        leaseAvailability = propertyDetails.leaseAvailability,
                        bedrooms = propertyDetails.bedrooms,
                        bathrooms = propertyDetails.bathrooms,
                        onClick = {
                            navController.navigate("$ROUTE_DETAILED_PROPERTY/${propertyDetails.address}")
                        }
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }

        }



        val image_list = listOf(
            PropertyDetails(address = "123 Main St", leaseAvailability = "April 1, 2024", imageId = R.drawable.img_2, bedrooms = 2, bathrooms = 4),
            PropertyDetails(address = "456 Elm St", leaseAvailability = "May 1, 2024", imageId = R.drawable.img_2, bedrooms = 1, bathrooms = 4),
            PropertyDetails(address = "123 Main St", leaseAvailability = "April 1, 2024", imageId = R.drawable.img_2, bedrooms = 2, bathrooms = 4),
            PropertyDetails(address = "456 Elm St", leaseAvailability = "May 1, 2024", imageId = R.drawable.img_2, bedrooms = 1, bathrooms = 4),
            PropertyDetails(address = "123 Main St", leaseAvailability = "April 1, 2024", imageId = R.drawable.img_2, bedrooms = 2, bathrooms = 2),
            PropertyDetails(address = "456 Elm St", leaseAvailability = "May 1, 2024", imageId = R.drawable.img_2, bedrooms = 1, bathrooms = 1),
            PropertyDetails(address = "123 Main St", leaseAvailability = "April 1, 2024", imageId = R.drawable.img_2, bedrooms = 2, bathrooms = 1),
            PropertyDetails(address = "456 Elm St", leaseAvailability = "May 1, 2024", imageId = R.drawable.img_2, bedrooms = 1, bathrooms = 1),
            PropertyDetails(address = "123 Main St", leaseAvailability = "April 1, 2024", imageId = R.drawable.img_2, bedrooms = 3, bathrooms = 2),
            PropertyDetails(address = "456 Elm St", leaseAvailability = "May 1, 2024", imageId = R.drawable.img_2, bedrooms = 2, bathrooms = 2),
            PropertyDetails(address = "123 Main St", leaseAvailability = "April 1, 2024", imageId = R.drawable.img_2, bedrooms = 2, bathrooms = 1),
            PropertyDetails(address = "456 Elm St", leaseAvailability = "May 1, 2024", imageId = R.drawable.img_2, bedrooms = 1, bathrooms = 1),
            PropertyDetails(address = "123 Main St", leaseAvailability = "April 1, 2024", imageId = R.drawable.img_2, bedrooms = 3, bathrooms = 2),
            PropertyDetails(address = "456 Elm St", leaseAvailability = "May 1, 2024", imageId = R.drawable.img_2, bedrooms = 2, bathrooms = 2)
        )

        ScrollableListWithImages(
            imageList = image_list,
            navController = navController
        )

    }


}


@Preview(showBackground = true)
@Composable
fun AllListingsPreview() {
    val navController = rememberNavController()
    AllListings(navController = navController)
}
