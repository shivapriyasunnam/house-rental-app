package com.example.house_rental_app.theme.screens.menuscreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.house_rental_app.data.PropertyDetails
import java.io.File

// Include MenuBar in your AddProperty composable
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddProperty(onPropertyAdded: (PropertyDetails) -> Unit, navController: NavController) {
    var imageId by remember { mutableStateOf(0) }
    var address by remember { mutableStateOf("") }
    var leaseAvailability by remember { mutableStateOf("") }
    var bedrooms by remember { mutableStateOf("0") }
    var bathrooms by remember { mutableStateOf("0") }
    var uploadedImage: File? by remember { mutableStateOf(null) }

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        MenuBar(navController = navController)

        Spacer(modifier = Modifier.height(16.dp))

        uploadedImage?.let { file ->
            val painter: Painter = painterResource(id = file.absolutePath.toInt())
            Image(
                painter = painter,
                contentDescription = "Uploaded Image",
                modifier = Modifier
                    .size(200.dp)
                    .padding(8.dp)
            )
        }

        OutlinedTextField(
            value = address,
            onValueChange = { address = it },
            label = { Text("Address") }
        )
        OutlinedTextField(
            value = leaseAvailability,
            onValueChange = { leaseAvailability = it },
            label = { Text("Lease Availability") }
        )
        OutlinedTextField(
            value = bedrooms,
            onValueChange = { bedrooms = it },
            label = { Text("Bedrooms") }
        )
        OutlinedTextField(
            value = bathrooms,
            onValueChange = { bathrooms = it },
            label = { Text("Bathrooms") }
        )

        Button(onClick = { /* Handle image selection */ }) {
            Text("Upload Image")
        }

        Button(
            onClick = {
                val propertyDetails = PropertyDetails(
                    imageId,
                    address,
                    leaseAvailability,
                    bedrooms.toInt(),
                    bathrooms.toInt()
                )
                onPropertyAdded(propertyDetails)
            }
        ) {
            Text("Add Property")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAddPropertyScreen() {
    val navController = rememberNavController()
    AddProperty(onPropertyAdded = {}, navController)
}