package com.example.house_rental_app.theme.screens.menuscreens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
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
import androidx.compose.runtime.rememberCoroutineScope
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
import kotlinx.coroutines.launch


@Composable
fun UserProfile(navController: NavController, sharedViewModel: SharedViewModel) {
    var editing by remember { mutableStateOf(false) }
    val userId = sharedViewModel.userId.observeAsState().value.toString().toInt()
    val coroutineScope = rememberCoroutineScope()
    val userViewModel: UserViewModel = viewModel()
    LaunchedEffect(userId) {
        userId.let {
            userViewModel.fetchUserById(it)
        }
    }
    val userDetails by userViewModel.userDetails.observeAsState()
    userDetails?.let { user ->
        var editedUser by remember { mutableStateOf(user) } // Remember the edited user state
        Column() {
            Spacer(modifier = Modifier.height(30.dp))

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(horizontal = 15.dp)
            ) {
                Text(text = "User Profile", style = MaterialTheme.typography.bodyLarge)
                Spacer(modifier = Modifier.height(16.dp))

                UserDetail("Email", if (editing) editedUser.emailId else user.emailId,
                    onValueChange = { newValue ->
                        editedUser = editedUser.copy(emailId = newValue) // Update edited user
                    },
                    isEditable = editing
                )

                UserDetail("Password", if (editing) editedUser.password else "*****",
                    onValueChange = { newValue ->
                        editedUser = editedUser.copy(password = newValue) // Update edited user
                    },
                    isEditable = false // Password should not be editable
                )

                UserDetail("Phone Number", if (editing) editedUser.phoneNumber else user.phoneNumber,
                    onValueChange = { newValue ->
                        editedUser = editedUser.copy(phoneNumber = newValue) // Update edited user
                    },
                    isEditable = editing
                )

                Spacer(modifier = Modifier.height(16.dp))

                if (editing) {
                    Button(onClick = {
                        coroutineScope.launch {
                            Log.println(Log.INFO, "Edited user", editedUser.toString())
                            userViewModel.updateUser(editedUser)}
                        editing = false
                    }) {
                        Text(text = "Save")
                    }
                } else {
                Button(
                    onClick = { editing = true },

                    modifier = Modifier
                        .border(
                            width = 1.dp,
                            color = Color.Black,
                            shape = RoundedCornerShape(4.dp)
                        )
                        .background(Color.LightGray)
                        .padding(horizontal = 19.dp, vertical = 2.dp),
                    // Add any additional styling here if needed
                ) {
                    Text(
                        text = "Edit",
                        style = TextStyle(color = Color.Black)
                    )
                }
            }
            }
        }
    } ?: run {
        Text(text = "User not found or loading")
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun UserDetail(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    isEditable: Boolean
) {
    Column(
        modifier = Modifier.padding(vertical = 8.dp)
    ) {
        Text(text = "$label: ", style = MaterialTheme.typography.bodyLarge, modifier = Modifier.padding(horizontal = 100.dp).align(Alignment.CenterHorizontally))
        if (isEditable) {
            TextField(
                value = value,
                onValueChange = onValueChange,
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email),
                modifier = Modifier.fillMaxWidth()
            )
        } else {
            Text(text = value, style = MaterialTheme.typography.bodyLarge)

        }
    }
}


@Preview(showBackground = true)
@Composable
fun UserProfilePreview() {
    val navController = rememberNavController()
    val sharedViewModel = SharedViewModel().apply { setUserId("8") }
    UserProfile(navController = navController, sharedViewModel = sharedViewModel)
}