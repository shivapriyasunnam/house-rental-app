package com.example.house_rental_app.theme.screens.profiless

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
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
import com.example.house_rental_app.data.profileviewmodel
import com.example.house_rental_app.navigation.ROUTE_CONTACTUS

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddProfile(navController: NavHostController){


    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally) {
        var context = LocalContext.current
        Text(
            text = "Add profile",
            fontSize = 30.sp,
            fontFamily = FontFamily.Default,
            color = Color.Black,
            modifier = Modifier.padding(20.dp),
            fontWeight = FontWeight.Bold,
            textDecoration = TextDecoration.Underline
        )

        var profileName by remember { mutableStateOf(TextFieldValue("")) }
        var profilehousenumber by remember { mutableStateOf(TextFieldValue("")) }
        var profilecontact by remember { mutableStateOf(TextFieldValue("")) }

        OutlinedTextField(
            value = profileName,
            onValueChange = { profileName = it },
            label = { Text(text = "Profile name *") },
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
            leadingIcon = {Icon(imageVector = Icons.Default.Person, contentDescription = "")}

        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = profilehousenumber,
            onValueChange = { profilehousenumber = it },
            label = { Text(text = "Profile Housenumber *") },
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
            leadingIcon = {Icon(imageVector = Icons.Default.Home, contentDescription = "")}

        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = profilecontact,
            onValueChange = { profilecontact = it },
            label = { Text(text = "Contact *") },
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
            leadingIcon = {Icon(imageVector = Icons.Default.Phone, contentDescription = "")}

        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = {
            //-----------WRITE THE SAVE LOGIC HERE---------------//
            var bookingRepository = profileviewmodel(navController,context)
            bookingRepository.saveProfile(profileName.text.trim(),profilehousenumber.text.trim(),
                profilecontact.text)



        },
            colors = ButtonDefaults.buttonColors(Color.Black)
            ) {
            Text(text = "Save")

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
fun Addpr(){}

