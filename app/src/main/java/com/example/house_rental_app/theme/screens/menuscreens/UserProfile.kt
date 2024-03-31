package com.example.house_rental_app.theme.screens.menuscreens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.house_rental_app.models.User



@Composable
fun UserProfile(user: User, onUserUpdated: (User) -> Unit, navController: NavController) {
    var editing by remember { mutableStateOf(false) }
    var editedUser by remember { mutableStateOf(user) }

    Column() {
        val currentRoute = getCurrentRoute(navController) ?: ""
        Log.println(Log.INFO, "current route", currentRoute)
        MenuBar(navController = navController, currentRoute = currentRoute)

        // Spacer to add some space between menu bar and list
        Spacer(modifier = Modifier.height(30.dp))

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(horizontal = 15.dp)
        ) {
            // Menu bar


            Text(text = "User Profile", style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(16.dp))

            UserDetail("Email", if (editing) editedUser.email else user.email) { newValue ->
                editedUser.email = newValue
            }

            UserDetail("Password", if (editing) editedUser.pass else "*****") { newValue ->
                editedUser.pass = newValue
            }

            UserDetail("UserID", if (editing) editedUser.userid else user.userid) { newValue ->
                editedUser.userid = newValue
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (editing) {
                TextButton(onClick = {
                    onUserUpdated(editedUser)
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

            if(label== "UserID"){
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
    val user = User("example@example.com", "password123", "12345")

    UserProfile(user = user, onUserUpdated = { /* Handle user update logic here */ }, navController = navController)
}