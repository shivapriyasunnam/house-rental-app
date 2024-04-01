package com.example.house_rental_app.theme.screens.menuscreens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.house_rental_app.data.SharedViewModel
import com.example.house_rental_app.entity.UserEntity
import com.example.house_rental_app.data.UserViewModel
import com.example.house_rental_app.models.User



@Composable
fun UserProfile(navController: NavController, sharedViewModel: SharedViewModel) {
    var editing by remember { mutableStateOf(false) }
    val userId = sharedViewModel.userId.observeAsState().value.toString().toInt()

    val userViewModel: UserViewModel = viewModel()
    LaunchedEffect(userId) {
        userId.let {
            userViewModel.fetchUserById(it)
        }
    }
    val userDetails by userViewModel.userDetails.observeAsState()
    userDetails?.let { user ->
        var editedUser by remember { mutableStateOf(user) }
    Column() {


//        val currentRoute = getCurrentRoute(navController) ?: ""
//        Log.println(Log.INFO, "current route", currentRoute)
//        MenuBar(navController = navController, currentRoute = currentRoute)

        // Spacer to add some space between menu bar and list
        Spacer(modifier = Modifier.height(30.dp))

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(horizontal = 15.dp)
        ) {
            // Menu bar


            Text(text = "User Profile", style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(16.dp))

            UserDetail("Email", if (editing) editedUser.emailId else user.emailId) { newValue ->
                editedUser.emailId = newValue
            }

            UserDetail("Password", if (editing) editedUser.password else "*****") { newValue ->
                editedUser.password = newValue
            }

            UserDetail("Phone Number", if(editing) editedUser.phoneNumber else user.phoneNumber) { newValue ->
                editedUser.phoneNumber = newValue
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (editing) {
                TextButton(onClick = {
                    userViewModel.updateUser(editedUser)
//                    onUserUpdated(editedUser)
                    editing = false
                }) {
                    Text(text = "Save")
                }
            } else {
                TextButton(
                    onClick = { run { editing = true } },
                    modifier = Modifier
                        .border(
                            width = 1.dp, // Border width
                            color = Color.Black, // Border color
                            shape = RoundedCornerShape(4.dp) // Border shape

                        )
                        .background(Color.LightGray)
                        .padding(horizontal = 19.dp, vertical = 2.dp) // Padding for the button
                ) {
                    Text(
                        text = "Edit",
                        style = TextStyle(color = Color.Black) // Text color
                    )
                }
            }
        }
    }
    }?:run{
        Text(text = "User not found or loading")
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun UserDetail(label: String, value: String, onValueChange: (String) -> Unit) {
    val context = LocalContext.current

    Column(
        modifier = Modifier.padding(vertical = 8.dp)
    ) {
        Text(text = "$label: ", style = MaterialTheme.typography.bodySmall)
        if ((value != "*****") && label != "UserID") {
            TextField(
                value = value,
                onValueChange = { onValueChange(it) },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email),
                modifier = Modifier.fillMaxWidth()
            )
        } else {

            if(label== "Phone Number"){
                TextField(
                    value = value,
                    onValueChange = { onValueChange(it) },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email),
                    modifier = Modifier.fillMaxWidth()
                )
            }
            else {

                Text(
                    text = "*****",
                    style = MaterialTheme.typography.headlineLarge,
                    modifier = Modifier.fillMaxWidth(),
                    maxLines = 1
                )
            }
//            Text(text = "*****", style = MaterialTheme.typography.bodySmall)
        }
    }
}



@Preview(showBackground = true)
@Composable
fun UserProfilePreview() {
    val navController = rememberNavController()
    val user = UserEntity(emailId = "example@example.com", password =  "password123", id = 12, username = "Jane Doe", phoneNumber = "23", showOnlyEmail = true, showOnlyPhone = false )

    UserProfile(navController = navController, sharedViewModel = SharedViewModel())
}