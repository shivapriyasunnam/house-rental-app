package com.example.house_rental_app.data

import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.house_rental_app.AppContainer
import com.example.house_rental_app.DatabaseApplication
import com.example.house_rental_app.MainActivity

object AppViewModelProvider {
//    val context by lazy { LocalContext.current as ComponentActivity }
    val Factory = viewModelFactory {
        initializer {
            UserViewModel()
        }
        initializer {
            HouseViewModel()
        }
    }
}