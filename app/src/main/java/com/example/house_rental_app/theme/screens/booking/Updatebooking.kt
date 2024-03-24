package com.example.house_rental_app.theme.screens.booking

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.house_rental_app.data.bookingviewmodel
import com.example.house_rental_app.models.Booking
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateBooking(navController: NavHostController,id:String) {
    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally) {
        var context = LocalContext.current
        var name by remember { mutableStateOf("") }
        var housenumber by remember { mutableStateOf("") }
        var contact by remember { mutableStateOf("") }

        var currentDataRef = FirebaseDatabase.getInstance().getReference()
            .child("Bookings/$id")
        currentDataRef.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                var booking = snapshot.getValue(Booking::class.java)
                name = booking!!.name
                housenumber = booking!!.sizeofthehouse
                contact = booking!!.locationofthehouse
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show()
            }
        })

        Text(
            text = "Add profile",
            fontSize = 30.sp,
            fontFamily = FontFamily.Cursive,
            color = Color.Red,
            modifier = Modifier.padding(20.dp),
            fontWeight = FontWeight.Bold,
            textDecoration = TextDecoration.Underline
        )

        var bookingname by remember { mutableStateOf(TextFieldValue(name)) }
        var bookingsizeofthehouse by remember { mutableStateOf(TextFieldValue(housenumber)) }
        var bookinglocationofthehouse by remember { mutableStateOf(TextFieldValue(contact)) }

        OutlinedTextField(
            value = bookingname,
            onValueChange = { bookingname = it },
            label = { Text(text = "Profile name *") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = bookingsizeofthehouse,
            onValueChange = { bookingsizeofthehouse = it },
            label = { Text(text = "Profile Housenumber *") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = bookinglocationofthehouse,
            onValueChange = { bookinglocationofthehouse = it },
            label = { Text(text = "Location of the house *") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = {
            //-----------WRITE THE UPDATE LOGIC HERE---------------//
            var bookingRepository = bookingviewmodel(navController, context)
            bookingRepository.updateBooking(bookingname.text.trim(),bookingsizeofthehouse.text.trim(),
                bookinglocationofthehouse.text.trim(),id)


        }) {
            Text(text = "Update")
        }

    }
}

