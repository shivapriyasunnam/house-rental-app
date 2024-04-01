package com.example.house_rental_app.theme.screens.menuscreens

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.runtime.mutableStateListOf
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
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

import com.example.house_rental_app.data.HouseViewModel
import com.example.house_rental_app.data.SharedViewModel
import com.example.house_rental_app.data.UserViewModel
import com.example.house_rental_app.entity.HouseEntity
import com.example.house_rental_app.navigation.ROUTE_MY_LISTINGS
import kotlinx.coroutines.launch
import java.io.FileOutputStream

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
    var imageCount by remember { mutableStateOf(0) }

    Log.println(Log.INFO, "Dengey", userId.value.toString())
    var imagePaths by remember { mutableStateOf<List<String>>(emptyList()) }
//    val imageUris: MutableList<String> = mutableListOf()
    var imageUris by remember { mutableStateOf<List<String>>(emptyList()) }

//    val pickImagesLauncher = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
//        if (result.resultCode == Activity.RESULT_OK) {
//
//            val data: Intent? = result.data
//            if (data?.clipData != null) {
//                // Handle multiple selected images
//                val clipData = data.clipData!!
//                for (i in 0 until clipData.itemCount) {
//                    val imageUri = clipData.getItemAt(i).uri
//                    imagePath = saveImageToInternalStorage(context, imageUri) // Save each image and get its path
//
//                    imageUris += (imageUri.toString())
//                }
//            } else if (data?.data != null) {
//                // Handle single image selection
//                data.data?.let { uri ->
//                    imagePath = saveImageToInternalStorage(context, uri) // Save the image and get its path
//                    imageUris += (uri.toString())
//                }
//            }
//            imageCount = imageUris.size
//
//            // Now imageUris contains all the selected images' URIs.
//            // Update your UI or database as needed.
//        }
//    }
    val pickImagesLauncher = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data
            val newImagePaths = mutableListOf<String>()

            if (data?.clipData != null) {
                // Handle multiple selected images
                val clipData = data.clipData!!
                for (i in 0 until clipData.itemCount) {
                    val imageUri = clipData.getItemAt(i).uri
                    val savedImagePath = saveImageToInternalStorage(context, imageUri) // Save each image and get its path
                    newImagePaths.add(savedImagePath)
                }
            } else if (data?.data != null) {
                // Handle single image selection
                data.data?.let { uri ->
                    val savedImagePath = saveImageToInternalStorage(context, uri) // Save the image and get its path
                    newImagePaths.add(savedImagePath)
                }
            }
            imagePaths = newImagePaths
        }
    }

    Log.println(Log.INFO, "Images", imagePaths.toString())

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
            OutlinedTextField(
                value = imageId,
                onValueChange = { imageId = it },
                label = { Text("Image ID (numeric)") },
                modifier = Modifier
                    .padding(bottom = 8.dp)
                    .fillMaxWidth()
            )
            Button(onClick = {
                    /* TODO: Add code for upload images*/

                val pickImageIntent = Intent(Intent.ACTION_PICK).apply {
                    type = "image/*"
                    putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
                }
                pickImagesLauncher.launch(pickImageIntent)
            }) {
                Text(text = "Upload Image")
            }
            if(imageCount > 0){
                Text(text = "$imageCount images uploaded!")
            }


            Button(
                onClick = {
                    val propertyDetails =
                        HouseEntity(ownerId = userId.value.toString().toInt(),
                            price = price.toInt(),
                            address = address,
                            images = imagePaths.joinToString(","),
                            description =  description,
                            lease = leaseAvailability
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
fun saveImageToInternalStorage(context: Context, uri: Uri): String {
    val bitmap = if (Build.VERSION.SDK_INT < 28) {
        MediaStore.Images.Media.getBitmap(context.contentResolver, uri)
    } else {
        val source = ImageDecoder.createSource(context.contentResolver, uri)
        ImageDecoder.decodeBitmap(source)
    }

    // Define the file path and name
    val filename = "image_${System.currentTimeMillis()}.jpg"
    val fos: FileOutputStream

    try {
        fos = context.openFileOutput(filename, Context.MODE_PRIVATE)
        // Use the compress method on the Bitmap object to write image to the OutputStream
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos)
        fos.close()
    } catch (e: Exception) {
        e.printStackTrace()
    }

    return context.getFileStreamPath(filename).absolutePath
}

//@Preview(showBackground = true)
//@Composable
//fun AddPropertyPreview() {
//    val navController = rememberNavController()
//    AddProperty(navController = navController, sharedViewModel = sharedViewModel)
//}