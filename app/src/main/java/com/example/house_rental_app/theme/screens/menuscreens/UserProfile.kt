package com.example.house_rental_app.theme.screens.menuscreens

import android.graphics.fonts.FontStyle
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
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
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.house_rental_app.data.SharedViewModel
import com.example.house_rental_app.entity.UserEntity
import com.example.house_rental_app.data.UserViewModel
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
            Spacer(modifier = Modifier.height(20.dp))


            Card(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                border = BorderStroke(2.dp, Color.White),
            ) {


                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = "My Profile", style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            fontSize = 25.sp,
                            fontFamily = FontFamily.Monospace,
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color.White),
                            textAlign = TextAlign.Center
                        )
                    }

                    Spacer(modifier = Modifier.height(25.dp))
                    if (!user.showOnlyEmail) {
                        UserDetail("Name", if (editing) editedUser.username else user.username,
                            onValueChange = { newValue ->
                                editedUser = editedUser.copy(username = newValue)
                            },
                            isEditable = false
                        )

                    }

                    UserDetail(
                        "Email", if (editing) editedUser.emailId else user.emailId,
                        onValueChange = { newValue ->
                            editedUser = editedUser.copy(emailId = newValue) // Update edited user
                        },
                        isEditable = false
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    UserDetail(
                        "Password", if (editing) editedUser.password else "*****",
                        onValueChange = { newValue ->
                            editedUser = editedUser.copy(password = newValue) // Update edited user
                        },
                        isEditable = false // Password should not be editable
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    UserDetail(
                        "Phone Number", if (editing) editedUser.phoneNumber else user.phoneNumber,
                        onValueChange = { newValue ->
                            editedUser =
                                editedUser.copy(phoneNumber = newValue) // Update edited user
                        },
                        isEditable = editing
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    if (editing) {
                        Button(modifier = Modifier.padding(horizontal = 19.dp, vertical = 2.dp),
                            onClick = {
                            coroutineScope.launch { userViewModel.updateUser(editedUser) }
                            editing = false
                        }) {
                            Text(text = "Save")
                        }
                    } else {
                        Button(
                            onClick = { editing = true },
                            modifier = Modifier
                                .padding(horizontal = 19.dp, vertical = 2.dp),
                            // Add any additional styling here if needed
                        ) {
                            Text(
                                text = "Edit",
                            )
                        }


                    }
                    Spacer(modifier = Modifier.height(20.dp))
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
    Column( horizontalAlignment = Alignment.CenterHorizontally
    ) {


            Text(
                text = "$label ", style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Monospace
            )
            if (isEditable) {
                Column {
                    TextField(
                        value = value,
                        onValueChange = onValueChange,
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email),
                        textStyle = TextStyle(color = Color.Black, textAlign = TextAlign.Center, fontSize = 20.sp),
                    )
                }

            } else {
                var value2 = value
                if (value2 == "") {
                    value2 = "Not Provided"
                }
                TextField(
                    value = value2,
                    enabled = false,
                    onValueChange = {},
                    textStyle = TextStyle(color = Color.Black, textAlign = TextAlign.Center, fontSize = 20.sp),
                )

            }
    }
}



