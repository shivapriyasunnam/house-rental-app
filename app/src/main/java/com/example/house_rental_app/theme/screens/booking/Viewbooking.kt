package com.example.house_rental_app.theme.screens.booking

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.house_rental_app.models.Booking
import com.example.house_rental_app.navigation.ROUTE_UPDATE_PROFILE
import com.example.house_rental_app.data.bookingviewmodel

@Composable
fun ViewBooking(navController:NavHostController) {
    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally) {



        var context = LocalContext.current
        var bookingRepository = bookingviewmodel(navController, context)


        val emptyBookingState = remember { mutableStateOf(Booking("","","","")) }
        var emptyBookingsListState = remember { mutableStateListOf<Booking>() }

        var Bookings = bookingRepository.viewbookings(emptyBookingState, emptyBookingsListState)


        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "All products",
                fontSize = 30.sp,
                fontFamily = FontFamily.Cursive,
                color = Color.Red)

            Spacer(modifier = Modifier.height(20.dp))
//            LazyColumn(){
//                items(Bookings){
//                    ProfileItem(
//                        name = it.name,
//                        housenumber = it.sizeofthehouse,
//                        contact = it.locationofthehouse,
//                        id = it.id,
//                        navController = navController ,
//                        bookingRepository= bookingRepository
//                    )
//                }
//            }





//
        }
    }
}




@Composable
fun BookingItem(name:String, sizeofthehouse:String, locationofthehouse:String, id:String,
                navController:NavHostController, bookingRepository: bookingviewmodel
) {



    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = name)
        Text(text = sizeofthehouse)
        Text(text = locationofthehouse)
        Button(onClick = {
            bookingRepository.deleteBooking(id)
        }) {
            Text(text = "Delete")
        }
        Button(onClick = {
            navController.navigate(ROUTE_UPDATE_PROFILE +"/$id")
        }) {
            Text(text = "Update")
        }
    }

}


