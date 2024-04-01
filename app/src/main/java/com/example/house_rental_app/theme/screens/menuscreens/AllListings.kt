package com.example.house_rental_app.theme.screens.menuscreens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.house_rental_app.R
import com.example.house_rental_app.entity.HouseEntity
import com.example.house_rental_app.navigation.ROUTE_DETAILED_PROPERTY

@Composable
fun AllListings(navController: NavController) {

    Column {
        // Menu bar

//        val currentRoute = getCurrentRoute(navController) ?: ""
//        Log.println(Log.INFO, "current route", currentRoute)
//        MenuBar(navController = navController, currentRoute = currentRoute)

        // Spacer to add some space between menu bar and list
        Spacer(modifier = Modifier.height(1.dp))

        @Composable
        fun ImageListItemAll(
            modifier: Modifier = Modifier,
            imageId: Int,
            address: String,
            leaseAvailability: String,
            price: Int,
            onClick: () -> Unit
        ) {
            Card(
                modifier = modifier
                    .padding(8.dp)
                    .clickable(onClick = onClick),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(16.dp)
                ) {
                    val painter: Painter = painterResource(id = imageId)
                    Image(
                        painter = painter,
                        contentDescription = null,
                        modifier = Modifier
                            .height(200.dp)
                            .fillMaxWidth().border(border = BorderStroke(2.dp, Color.White))
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "Address: $address",
                        style = MaterialTheme.typography.bodyLarge,
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = "Lease Available From: $leaseAvailability",
                        style = MaterialTheme.typography.bodyLarge,
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = "Price: $price",
                        style = MaterialTheme.typography.bodyLarge,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }


        @Composable
        fun ScrollableListWithImagesAll(
            houseEntities: List<HouseEntity>,
            navController: NavController
        ) {
            LazyColumn {
                items(houseEntities) { houseEntity ->
                    ImageListItemAll(
                        imageId = houseEntity.images.toInt(),
                        address = houseEntity.address,
                        leaseAvailability = houseEntity.lease,
                        price = houseEntity.price,
                        onClick = {
                            navController.navigate("$ROUTE_DETAILED_PROPERTY/${houseEntity.houseId}")
                        }
                    )
                }
            }
        }



        val image_list = listOf(
            HouseEntity( address = "111 Main St", price = 234, ownerId = 23, lease = "April 1st 2022", images =
                R.drawable.img_1 .toString(), description = "A 2 bedroom" ),
            HouseEntity( address = "111 Main St", price = 234, ownerId = 23, lease = "April 1st 2022", images =
            R.drawable.rentalfive .toString(), description = "A 2 bedroom" ),
            HouseEntity( address = "111 Main St", price = 234, ownerId = 23, lease = "April 1st 2022", images =
            R.drawable.rentalfour.toString(), description = "A 2 bedroom" ),
            HouseEntity( address = "111 Main St", price = 234, ownerId = 23, lease = "April 1st 2022", images =
            R.drawable.rentalfive.toString(), description = "A 2 bedroom" ),
            HouseEntity( address = "111 Main St", price = 234, ownerId = 23, lease = "April 1st 2022", images =
            R.drawable.rentalone.toString(), description = "A 2 bedroom" ),
            HouseEntity( address = "111 Main St", price = 234, ownerId = 23, lease = "April 1st 2022", images =
            R.drawable.rentalfive.toString(), description = "A 2 bedroom" ),
            HouseEntity( address = "111 Main St", price = 234, ownerId = 23, lease = "April 1st 2022", images =
            R.drawable.rentalsix.toString(), description = "A 2 bedroom" ),
            HouseEntity( address = "111 Main St", price = 234, ownerId = 23, lease = "April 1st 2022", images =
            R.drawable.rentalthree.toString(), description = "A 2 bedroom" )





        )

        ScrollableListWithImagesAll(
            houseEntities = image_list,
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
