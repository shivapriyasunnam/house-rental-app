package com.example.house_rental_app.theme.screens.menuscreens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.house_rental_app.R
import com.example.house_rental_app.data.PropertyDetails
import com.example.house_rental_app.navigation.ROUTE_DETAILED_PROPERTY
import java.io.File

// Include MenuBar in your AddProperty composable
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddProperty(navController: NavController) {
    val context = LocalContext.current

    // State for each property detail
    var imageId by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var leaseAvailability by remember { mutableStateOf("") }
    var bedrooms by remember { mutableStateOf("") }
    var bathrooms by remember { mutableStateOf("") }

    Column() {

        MenuBar(navController = navController)

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
                value = bedrooms,
                onValueChange = { bedrooms = it },
                label = { Text("Price") },
                modifier = Modifier
                    .padding(bottom = 8.dp)
                    .fillMaxWidth()
            )
            OutlinedTextField(
                value = bathrooms,
                onValueChange = { bathrooms = it },
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
                    val details =
                        "Image ID: $imageId, Address: $address, Lease: $leaseAvailability, Bedrooms: $bedrooms, Bathrooms: $bathrooms"
                    Toast.makeText(context, "Added to Listing", Toast.LENGTH_LONG).show()
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


@Preview(showBackground = true)
@Composable
fun AddPropertyPreview() {
    val navController = rememberNavController()
    AddProperty(navController = navController)
}