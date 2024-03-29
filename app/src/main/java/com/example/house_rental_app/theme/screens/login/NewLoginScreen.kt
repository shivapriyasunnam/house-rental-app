package com.example.house_rental_app.theme.screens.login


import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.house_rental_app.R
import com.example.house_rental_app.data.NewAuthViewModel
import com.example.house_rental_app.navigation.ROUTE_HOME
import com.example.house_rental_app.navigation.ROUTE_NEWREGISTER
import com.example.house_rental_app.navigation.ROUTE_REGISTER

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewLoginScreen(navController: NavHostController, viewModel: NewAuthViewModel = viewModel()) {
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
            Text(text = "Login", style = MaterialTheme.typography.headlineSmall)

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
                keyboardActions = KeyboardActions(onDone = {
                    viewModel.loginUser(email.value, password.value) { user ->
                        if (user != null) {
                            navController.navigate(ROUTE_HOME)
                        } else {
                            Toast.makeText(
                                context,
                                "Invalid email or password. Please try again.",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                })
            )

            Button(
                onClick = {
                    viewModel.loginUser(email.value, password.value) { user ->
                        if (user != null) {
                            navController.navigate(ROUTE_HOME)
                        } else {
                            Toast.makeText(
                                context,
                                "Invalid email or password. Please try again.",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                },
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text("Login")
            }

            Button(
                onClick = {
                    navController.navigate(ROUTE_NEWREGISTER)
                },
                colors = ButtonDefaults.buttonColors(Color.Black),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 37.dp)
            ) {
                Text(text = "Don't have an account? Click to Register", fontSize = 15.sp)
            }
        }
    }
}
@Preview
@Composable
fun NewLoginScreePreview(){
    NewLoginScreen(rememberNavController())
}