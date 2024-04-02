package com.example.house_rental_app.theme.screens.property

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.house_rental_app.data.HouseViewModel
import com.example.house_rental_app.entity.HouseEntity
import com.example.house_rental_app.navigation.ROUTE_CONTACT_LANDLORD
import com.example.house_rental_app.theme.screens.menuscreens.loadBitmapFromFilePath
import com.example.house_rental_app.theme.screens.menuscreens.toAnnotatedText

@Composable
fun DetailedProperty(navController: NavController, houseId: Int) {

    val houseViewModel: HouseViewModel = viewModel()
    val houseEntity by houseViewModel.viewedHouse.observeAsState()
    LaunchedEffect(houseId) {
        houseViewModel.getHouseById(houseId)
    }
    val imagePaths = houseEntity?.images?.split(",")?.map { it.trim() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LazyRow{
        // Show property image
        if (imagePaths != null) {

            items(imagePaths) { imagePath ->
                val bitmap = loadBitmapFromFilePath(imagePath)
                bitmap?.let {
                    Image(
                        bitmap = it.asImageBitmap(),
                        contentDescription = "Image",
                        modifier = Modifier
                            .height(200.dp)
                            .fillMaxWidth()
                    )
                }
            }
        }}

        // Show property details
        Text(toAnnotatedText(text = "Address:  ", houseEntity?.address ?: ""), style = MaterialTheme.typography.labelLarge,)
        Text(toAnnotatedText(text = "Lease Availability: ", houseEntity?.lease?:""), style = MaterialTheme.typography.labelLarge,)
        Text(toAnnotatedText(text = "Price: ", houseEntity?.price.toString() ?: "0"), style = MaterialTheme.typography.labelLarge,)
        Text(toAnnotatedText(text = "Description:  ", houseEntity?.description ?: ""), style = MaterialTheme.typography.labelLarge,)

        // Button to contact landlord
        Button(onClick = {
            // Ensure ownerId is present. Replace '0' with a sensible default or handle the case where ownerId is null.
            val ownerId = houseEntity?.ownerId ?: 0
            navController.navigate("$ROUTE_CONTACT_LANDLORD/$ownerId")
        }) {
            Text("Contact Landlord")
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun DetailedPropertyPreview() {
//    val navController = rememberNavController()
//    DetailedProperty(navController = navController,  HouseEntity( address = "111 Main St", price = 234, ownerId = 23, lease = "April 1st 2022", images = "img_2", description = "A 2 bedroom" ))
//}
