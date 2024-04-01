package com.example.house_rental_app.theme.screens.menuscreens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

import com.example.house_rental_app.data.HouseViewModel
import com.example.house_rental_app.data.SharedViewModel
import com.example.house_rental_app.data.UserViewModel
import com.example.house_rental_app.entity.HouseEntity
import com.example.house_rental_app.navigation.ROUTE_MY_LISTINGS
import kotlinx.coroutines.launch

// Include MenuBar in your AddProperty composable
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddProperty(navController: NavController, sharedViewModel: SharedViewModel) {
    val context = LocalContext.current
    val userViewModel: UserViewModel = viewModel()
    val user by userViewModel.currentUser.observeAsState()
    val houseViewModel : HouseViewModel = viewModel()
    // State for each property detail
    var imageId by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var leaseAvailability by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()
    val userId = sharedViewModel.userId.observeAsState()
    Log.println(Log.INFO, "Dengey", userId.value.toString())


    Column() {

//        val currentRoute = getCurrentRoute(navController) ?: ""
//        Log.println(Log.INFO, "current route", currentRoute)
//        MenuBar(navController = navController, currentRoute = currentRoute)

        // Spacer to add some space between menu bar and list
        Spacer(modifier = Modifier.height(1.dp))

        Text(
            text = "Add Property to List",
            fontSize = 20.sp,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize(Alignment.Center)

        )

        Column(
            modifier = Modifier
                .padding(10.dp)
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            // Input fields
            OutlinedTextField(
                value = imageId,
                onValueChange = { imageId = it },
                label = { Text("Image ID (numeric)") },
                modifier = Modifier
                    .padding(bottom = 8.dp)
                    .fillMaxWidth()
            )
            OutlinedTextField(
                value = address,
                onValueChange = { address = it },
                label = { Text("Address") },
                modifier = Modifier
                    .padding(bottom = 8.dp)
                    .fillMaxWidth()
            )
            OutlinedTextField(
                value = leaseAvailability,
                onValueChange = { leaseAvailability = it },
                label = { Text("Lease Availability") },
                modifier = Modifier
                    .padding(bottom = 8.dp)
                    .fillMaxWidth()
            )
            OutlinedTextField(
                value = price,
                onValueChange = { price = it },
                label = { Text("Price") },
                modifier = Modifier
                    .padding(bottom = 8.dp)
                    .fillMaxWidth()
            )
            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Description") },
                modifier = Modifier
                    .padding(bottom = 8.dp)
                    .fillMaxWidth()
                    .size(200.dp)
            )

            //Price
            //Description

            Button(
                onClick = {
                    // Here you'd typically collect the data and use it
                    // For example, send it to a ViewModel or directly to a database
                    // Note: Make sure to validate and convert numeric fields appropriately
//                    val details =
//                        "Image ID: $imageId, Address: $address, Lease: $leaseAvailability, Bedrooms: $bedrooms, Bathrooms: $bathrooms"
//                    Log.println(Log.INFO, "Dengey", currentUser.value.toString())
                    val propertyDetails =
                        HouseEntity(ownerId = userId.value.toString().toInt(),
                            price = price.toInt(),
                            address = address,
                            images = "",
                            description =  description,
                            lease = ""
                        )


                    coroutineScope.launch {
                            houseViewModel.addHouse(propertyDetails)

                    }

                    Toast.makeText(context, "Added to Listing", Toast.LENGTH_LONG).show()
                    navController.navigate(ROUTE_MY_LISTINGS)

                },
                modifier = Modifier
                    .padding(top = 16.dp,)
                    .fillMaxWidth()
                    .padding(horizontal = 50.dp)
            ) {
                Text("Submit")
            }
        }
    }

}


//@Preview(showBackground = true)
//@Composable
//fun AddPropertyPreview() {
//    val navController = rememberNavController()
//    AddProperty(navController = navController, sharedViewModel = sharedViewModel)
//}