package com.example.house_rental_app.theme.screens.Register

// NewRegisterScreen.kt

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.house_rental_app.R
import com.example.house_rental_app.data.NewAuthViewModel

import com.example.house_rental_app.navigation.ROUTE_NEWLOGIN

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewRegisterScreen(navController: NavHostController, viewModel: NewAuthViewModel = viewModel()) {
    val context = LocalContext.current
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.img_2), contentDescription = null,
            modifier = Modifier.fillMaxSize().alpha(0.6f), contentScale = ContentScale.FillHeight,
            alignment = Alignment.Center
        )
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Register here!", style = MaterialTheme.typography.headlineSmall)


            OutlinedTextField(
                value = email.value,
                onValueChange = { email.value = it },
                label = { Text("Email") },
                modifier = Modifier.padding(top = 16.dp),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
            )

            OutlinedTextField(
                value = password.value,
                onValueChange = { password.value = it },
                label = { Text("Password") },
                modifier = Modifier.padding(top = 16.dp),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            )

            Button(
                onClick = {
                    viewModel.registerUser(email.value, password.value) { success ->
                        if (success) {
                            Toast.makeText(context, "Registration successful", Toast.LENGTH_LONG)
                                .show()
                        } else {
                            Toast.makeText(
                                context,
                                "Registration failed. Please try again.",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                },
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text("Register")
            }

            Spacer(modifier = Modifier.height(20.dp))
            Button(
                onClick = {
                    navController.navigate(ROUTE_NEWLOGIN)
                },
                colors = ButtonDefaults.buttonColors(Color.Black)
            ) {
                Text(text = "Already have an Account? Click to Login")

            }
        }
    }
}
@Preview
@Composable
fun NewRegisterpage(){
    NewRegisterScreen(rememberNavController())
}

