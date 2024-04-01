package com.example.house_rental_app.theme.screens.menuscreens

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.house_rental_app.R
import com.example.house_rental_app.data.HouseViewModel
import com.example.house_rental_app.data.SharedViewModel
import com.example.house_rental_app.data.UserViewModel
import com.example.house_rental_app.entity.HouseEntity
import com.example.house_rental_app.navigation.ROUTE_DETAILED_PROPERTY
import com.example.house_rental_app.theme.screens.LoadingPage
import kotlinx.coroutines.delay

@Composable
fun AllListings(navController: NavController, sharedViewModel: SharedViewModel) {

    val userViewModel: UserViewModel = viewModel()
    val user by userViewModel.currentUser.observeAsState()
    Log.println(Log.INFO, "",user.toString())
    val houseViewModel: HouseViewModel = viewModel()
    val allHouses by houseViewModel.allHouses.observeAsState(emptyList())
    var isLoading by remember { mutableStateOf(true) }

    Log.println(Log.INFO, "", allHouses.toString())
    LaunchedEffect(key1 = true) {
        // Simulate a network loading delay or wait for a real network call
        delay(3000) // Remove this line if you are observing real data changes
        isLoading = false
    }
    if (isLoading) {
        LoadingPage("Fetching listings...")
    } else {
        Column {
            // Spacer to add some space between menu bar and list
            Spacer(modifier = Modifier.height(1.dp))

            ScrollableListWithImagesAll(
                houseEntities = allHouses,
                navController = navController
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
                houseEntity = houseEntity,
                onClick = {
                    navController.navigate("$ROUTE_DETAILED_PROPERTY/${houseEntity.houseId}")
                }
            )
        }
    }
}
@Composable
fun ImageListItemAll(
    houseEntity: HouseEntity,
    onClick: () -> Unit
) {
    val imagePaths = houseEntity.images.split(",").map { it.trim() }
    Card(
        modifier = Modifier
            .padding(8.dp)
            .clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(16.dp)
        ) {
            LazyRow {
                items(imagePaths) { imagePath ->
                    val bitmap = loadBitmapFromFilePath(imagePath)
                    bitmap?.let {
                        Image(
                            bitmap = it.asImageBitmap(),
                            contentDescription = "Property Image",
                            modifier = Modifier
                                .size(200.dp)
                                .padding(4.dp)
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Address: ${houseEntity.address}",
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center
            )
            Text(
                text = "Lease Available From: ${houseEntity.lease}",
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center
            )
            Text(
                text = "Price: ${houseEntity.price}",
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AllListingsPreview() {
    val navController = rememberNavController()
    val sharedViewModel = SharedViewModel().apply { setUserId("") }
    AllListings(navController = navController, sharedViewModel = sharedViewModel)
}
