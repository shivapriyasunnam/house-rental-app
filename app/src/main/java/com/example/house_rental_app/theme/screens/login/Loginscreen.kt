package com.example.house_rental_app.theme.screens.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
//import com.airbnb.lottie.R
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.house_rental_app.data.AuthViewModel
import com.example.house_rental_app.navigation.ROUTE_REGISTER
import com.example.house_rental_app.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Loginscreen(navController: NavHostController){
    var email by remember { mutableStateOf(TextFieldValue("")) }
    var pass by remember { mutableStateOf(TextFieldValue("")) }
    val context = LocalContext.current

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(top=20.dp)
    ){
        Spacer(modifier = Modifier.height(50.dp))
        val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.animation_lmz7yxkm))
        val progress by animateLottieCompositionAsState(composition )

        LottieAnimation(composition , progress,
            modifier =Modifier.size(50.dp))
    }
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally){
        Text(text="Login here",
            color = Color.Black,
            fontFamily = FontFamily.Default,
            fontSize=30.sp
        )
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(value = email, onValueChange = {email=it},
            label={ Text(text="Enter Email") },
            keyboardOptions = KeyboardOptions.Default.copy(imeAction= ImeAction.Next),
            modifier= Modifier
                .fillMaxWidth()
                .padding(8.dp),
            colors =TextFieldDefaults.run {
                textFieldColors(
                    textColor = Color.Black,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            },
            leadingIcon = { Icon(imageVector= Icons.Default.Email,contentDescription="") }


            )
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(value = pass, onValueChange ={pass=it} ,
            label={ Text(text = "Enter password") },
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
            modifier = Modifier
                .fillMaxWidth()
                .padding(),
            colors =TextFieldDefaults.run {
                textFieldColors(
                    textColor = Color.Black,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            },
            leadingIcon = { Icon(imageVector= Icons.Default.Lock,contentDescription="") }
        )
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = {
            val yyy= AuthViewModel(navController, context)
            yyy.login(email.text.trim(),pass.text.trim())

        },
            colors =ButtonDefaults.buttonColors(Color.Black),
            modifier = Modifier.fillMaxWidth()) {
            Text(text = "Log In")

        }
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = {
            navController.navigate(ROUTE_REGISTER)
        },
            colors = ButtonDefaults.buttonColors(Color.Black))
         {
            Text(text = "Don't have an account?Click to Register")

        }
    }


}
@Preview
@Composable
fun LoginscreePreview(){
    Loginscreen(rememberNavController())
}