package com.example.house_rental_app.theme.screens.menuscreens

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.house_rental_app.R
import com.example.house_rental_app.entity.HouseEntity
import com.example.house_rental_app.data.HouseViewModel
import com.example.house_rental_app.data.SharedViewModel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyListings(navController: NavController, sharedViewModel: SharedViewModel) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    val houseViewModel: HouseViewModel = viewModel()
    val userId = sharedViewModel.userId.observeAsState().value.toString().toInt()
    LaunchedEffect(key1 = userId) {
        houseViewModel.viewHousesByOwnerId(userId)
    }
    val allHouses by houseViewModel.allHouses.observeAsState(initial = emptyList())
    Log.println(Log.INFO, "", allHouses.toString())

    //Each Property Card
    Column {
        Spacer(modifier = Modifier.height(1.dp))

        @SuppressLint("RememberReturnType")
        @Composable
        fun ImageListItem(
            modifier: Modifier = Modifier,
            houseEntity: HouseEntity,
            onEditClick: () -> Unit,
            onSaveClick: (HouseEntity) -> Unit
        ) {
            var isEditing by remember { mutableStateOf(false) }
//            val trimmedImages = houseEntity.images.trimStart('[').trimEnd(']')
//
//            val imagesList: List<String> = trimmedImages.split(", ")
            val imagePaths = houseEntity.images.split(",").map { it.trim() }
//            val painter: Painter = painterResource(id = houseEntity.images.toInt())
            Card(
                modifier = modifier
                    .padding(8.dp),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Column(
                    modifier = modifier.fillMaxWidth().clickable { },
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    LazyRow {
                        items(imagePaths) { imagePath ->
                            Card(
                                modifier = Modifier
                                    .padding(8.dp)
                                    .width(200.dp),

                            ) {
//                                val uri = Uri.parse(imagePath)
                                val bitmap = remember { loadBitmapFromFilePath(imagePath)}
                                bitmap?.let {
                                    Image(
                                        bitmap = it.asImageBitmap(),
                                        contentDescription = "Image",
                                        contentScale = ContentScale.Crop,
                                        modifier = Modifier.height(250.dp)
                                            .fillMaxWidth()
                                    )
                                }
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(10.dp))

                    if (isEditing) {
                        var address by remember { mutableStateOf(houseEntity.address) }
                        var leaseAvailability by remember { mutableStateOf(houseEntity.lease) }
                        // TextFields for editing
                        TextField(
                            value = address,
                            onValueChange = { address = it },
                            label = { Text("Address") })
                        TextField(
                            value = leaseAvailability,
                            onValueChange = { leaseAvailability = it },
                            label = { Text("Lease Availability") })
                        Button(onClick = {
                            onSaveClick(
                                //TODO EDIT OP
                                houseEntity.copy(
                                    address = address,
                                    lease = leaseAvailability
                                )
                            )
                            isEditing = false
                        }) {
                            Text("Save")
                        }
                    } else {
                        // Display text with an edit button
                        Text("Address: ${houseEntity.address}", textAlign = TextAlign.Center)
                        Text(
                            "Lease Available From: ${houseEntity.lease}",
                            textAlign = TextAlign.Center
                        )
                        Row() {
                            IconButton(onClick = { isEditing = true }) {
                                Icon(Icons.Default.Edit, contentDescription = "Edit")
                            }
                            IconButton(onClick = {
                                //TODO DELETE OP
                                coroutineScope.launch{
                                    houseViewModel.deleteHouse(houseEntity)
                                }
                                Toast.makeText(context, "Deleting your Listing..", Toast.LENGTH_LONG).show()

                            }) {
                                Icon(Icons.Default.Delete, contentDescription = "Edit")
                            }
                        }
                    }
                }
            }
        }


        @Composable
        fun ScrollableListWithImages(
            houseEntities: List<HouseEntity>,
            navController: NavController
        ) {
            LazyColumn {
                items(houseEntities) { houseEntity ->
                    ImageListItem(
                        houseEntity = houseEntity,
                        onEditClick = { /* Handle edit click */ },
                        onSaveClick = { updatedDetails ->
                            // Handle the save action, e.g., update the list or backend
                            Log.d("EditProperty", "Saved: $updatedDetails")
                        }
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }
        }





        val image_list = listOf(
            HouseEntity( address = "111 Main St", price = 234, ownerId = 23, lease = "April 1st 2022", images =
            R.drawable.rentalfour.toString(), description = "A 2 bedroom" ),
            HouseEntity( address = "111 Main St", price = 234, ownerId = 23, lease = "April 1st 2022", images =
            R.drawable.rentalthree.toString(), description = "A 2 bedroom" ),
            HouseEntity( address = "111 Main St", price = 234, ownerId = 23, lease = "April 1st 2022", images =
            R.drawable.rentalnine.toString(), description = "A 2 bedroom" )
        )

        ScrollableListWithImages(
            houseEntities = allHouses,
            navController = navController
        )

    }


}
fun loadBitmapFromUri(context: Context, uri: Uri): Bitmap? {
    return try{
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            // For Android P and above
            val source = ImageDecoder.createSource(context.contentResolver, uri)
            ImageDecoder.decodeBitmap(source)
        } else {
            // For older versions
            @Suppress("DEPRECATION")
            MediaStore.Images.Media.getBitmap(context.contentResolver, uri)
        }
    } catch(e:Exception){
        Log.e("MyApp", "Error loading image from URI: $uri", e)
        null
    }
}
fun loadBitmapFromFilePath(filePath: String): Bitmap? {
    return try {
        BitmapFactory.decodeFile(filePath)
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}

//@Preview(showBackground = true)
//@Composable
//fun MyListingsPreview() {
//    val navController = rememberNavController()
//    MyListings(navController = navController, sharedViewModel = sharedViewModel)
//}
