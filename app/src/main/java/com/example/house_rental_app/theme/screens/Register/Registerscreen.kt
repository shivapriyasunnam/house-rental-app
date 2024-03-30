package com.example.house_rental_app.theme.screens.Register

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.house_rental_app.R
import com.example.house_rental_app.data.AppViewModelProvider
import com.example.house_rental_app.data.UserDetails
import com.example.house_rental_app.data.UserViewModel
import com.example.house_rental_app.entity.UserEntity
import com.example.house_rental_app.navigation.ROUTE_LOGIN
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(navController: NavHostController){
    var email by remember { mutableStateOf(TextFieldValue("")) }
    var pass by remember { mutableStateOf(TextFieldValue("")) }
    var confirmpass by remember { mutableStateOf(TextFieldValue("")) }
    var isHomeRenter by remember { mutableStateOf(false) }
    var isHomeOwner by remember { mutableStateOf(false) }
    var context= LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val userViewModel: UserViewModel = viewModel(factory = AppViewModelProvider.Factory)
    val onChange = userViewModel :: updateUserUiState
    val itemUiState = userViewModel.userUiState
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(top=20.dp)
    ){
        Spacer(modifier = Modifier.height(50.dp))
        val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.animation_lmrmwnsa))
        val progress by animateLottieCompositionAsState(composition )

        LottieAnimation(composition , progress,
            modifier =Modifier.size(50.dp))
    }


    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // Background Image
        Image(
            painter = painterResource(id = R.drawable.img_2), contentDescription = null,
            modifier = Modifier.fillMaxSize().alpha(0.6f), contentScale = ContentScale.FillHeight,
            alignment = Alignment.Center
        )

        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Register Here!",
                color = Color.Black,
                fontFamily = FontFamily.Default,
                fontSize = 30.sp,
                modifier = Modifier.padding(20.dp)
            )
            Spacer(modifier = Modifier.height(20.dp))
            OutlinedTextField(value = email,
                onValueChange = {
                    email = it
//                    itemUiState.copy(email = it)
                                },
                label = { Text(text = "Enter Email") },
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp)
                    ,
                colors = TextFieldDefaults.run {
                    textFieldColors(
                        textColor = Color.Black,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    )
                },
                singleLine = true,
                leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = "") }
            )

//            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(value = pass, onValueChange = { pass = it },
                label = { Text(text = "Enter password") },
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp),
                colors = TextFieldDefaults.run {
                    textFieldColors(
                        textColor = Color.Black,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    )
                },
                singleLine = true,

                leadingIcon = { Icon(imageVector = Icons.Default.Lock, contentDescription = "") }
            )
//            Spacer(modifier = Modifier.height(20.dp))
            OutlinedTextField(value = confirmpass, onValueChange = { confirmpass = it },
                label = { Text(text = "Confirm password") },
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp),
                colors = TextFieldDefaults.run {
                    textFieldColors(
                        textColor = Color.Black,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    )
                },
                singleLine = true,
                leadingIcon = { Icon(imageVector = Icons.Default.Lock, contentDescription = "") }
            )



            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(all = 16.dp).background(Color.White)
            ) {
                Text(
                    text = "You are a ",
                    modifier = Modifier.padding(all = 10.dp)
                )

                Checkbox(
                    checked = isHomeRenter,
                    onCheckedChange = { isHomeRenter = it }
                )
                Text(
                    text = "Home Renter",
                    modifier = Modifier.clickable { isHomeRenter = !isHomeRenter }
                )

                Checkbox(
                    checked = isHomeOwner,
                    onCheckedChange = { isHomeOwner = it }
                )
                Text(
                    text = "Home Owner ",
                    modifier = Modifier.clickable { isHomeOwner = !isHomeOwner }
                )
            }


            Button(
                onClick = {
                    itemUiState.copy(userDetails = UserDetails(username = "", email=email.text.trim(), password=pass.text.trim() ))

                    val user = toUserEntity(email.text.trim(), pass.text.trim(), confirmpass.text.trim())
                    coroutineScope.launch {
                        userViewModel.registerUser(user)
                    }
                },
                colors = ButtonDefaults.buttonColors(Color.Black),
                modifier = Modifier.fillMaxWidth().padding(horizontal = 50.dp)
            ) {
                Text(text = "Register")

            }
            Spacer(modifier = Modifier.height(20.dp))
            Button(
                onClick = {
                    navController.navigate(ROUTE_LOGIN)
                },
                colors = ButtonDefaults.buttonColors(Color.Black)
            ) {
                Text(text = "Already have an Account? Click to Login")

            }
        }
    }

}

fun toUserEntity(email: String, password: String, confirmPassword: String): UserEntity {
    return UserEntity(0,"",password,email, "",true, true)
}

@Preview
@Composable
fun Registerpage(){
    RegisterScreen(rememberNavController())
}

