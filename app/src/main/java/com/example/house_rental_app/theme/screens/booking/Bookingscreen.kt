package com.example.house_rental_app.theme.screens.booking

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.house_rental_app.data.bookingviewmodel
import com.example.house_rental_app.navigation.ROUTE_CONTACTUS


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookingScreen(navController: NavHostController){


    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally) {
        var context = LocalContext.current
        Text(
            text = "Book Here",
            fontSize = 30.sp,
            fontFamily = FontFamily.Default,
            color = Color.Black,
            modifier = Modifier.padding(20.dp),
            fontWeight = FontWeight.Bold,
            textDecoration = TextDecoration.Underline
        )

        var bookingname by remember { mutableStateOf(TextFieldValue("")) }
        var bookingsizeofthehouse by remember { mutableStateOf(TextFieldValue("")) }
        var bookinglocationofthehouse by remember { mutableStateOf(TextFieldValue("")) }

        OutlinedTextField(
            value = bookingname,
            onValueChange = { bookingname = it },
            label = { Text(text = "Your Name *") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            colors = TextFieldDefaults.run {
                textFieldColors(
                    textColor = Color.Black,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            },
            leadingIcon = { Icon(imageVector = Icons.Default.Person, contentDescription = "") }

        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = bookingsizeofthehouse,
            onValueChange = { bookingsizeofthehouse = it },
            label = { Text(text = "The size of the house you want *") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            colors = TextFieldDefaults.run {
                textFieldColors(
                    textColor = Color.Black,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            },
            leadingIcon = { Icon(imageVector = Icons.Default.Home, contentDescription = "") }

        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = bookinglocationofthehouse,
            onValueChange = { bookinglocationofthehouse = it },
            label = { Text(text = "Location of the house *") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            colors = TextFieldDefaults.run {
                textFieldColors(
                    textColor = Color.Black,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            },
            leadingIcon = { Icon(imageVector = Icons.Default.LocationOn, contentDescription = "") }

        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = {
            //-----------WRITE THE SAVE LOGIC HERE---------------//
            var bookingRepository = bookingviewmodel(navController,context)
            bookingRepository.saveBooking(bookingname.text.trim(),bookingsizeofthehouse.text.trim(),
                bookinglocationofthehouse.text)



        },
            colors = ButtonDefaults.buttonColors(Color.Black)
        ) {
            Text(text = "Submit")

        }
        Button(
            onClick = {
                navController.navigate(ROUTE_CONTACTUS)
            },
            colors = ButtonDefaults.buttonColors(Color.Black),
            modifier = Modifier.fillMaxWidth()
        )
        {
            Text(text = "Please contact us in case of any querries")

        }

    }
}
@Preview
@Composable
fun Addbooks(){
    BookingScreen(rememberNavController())
}
    



