package com.example.house_rental_app.navigation

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.house_rental_app.R
import com.example.house_rental_app.entity.HouseEntity
import com.example.house_rental_app.entity.UserEntity

import com.example.house_rental_app.data.SharedViewModel
import com.example.house_rental_app.theme.screens.Register.NewRegisterScreen
import com.example.house_rental_app.theme.screens.Register.RegisterScreen
import com.example.house_rental_app.theme.screens.contactus.ContactUs
import com.example.house_rental_app.theme.screens.home.HomeScreen
import com.example.house_rental_app.theme.screens.login.Loginscreen
import com.example.house_rental_app.theme.screens.login.NewLoginScreen
import com.example.house_rental_app.theme.screens.menuscreens.AddProperty
import com.example.house_rental_app.theme.screens.menuscreens.AllListings
import com.example.house_rental_app.theme.screens.menuscreens.MyListings
import com.example.house_rental_app.theme.screens.menuscreens.UserProfile

import com.example.house_rental_app.theme.screens.profiless.AddProfile
import com.example.house_rental_app.theme.screens.profiless.Updateprofile
import com.example.house_rental_app.theme.screens.profiless.Viewprofile
import com.example.house_rental_app.theme.screens.property.DetailedProperty
import com.example.house_rental_app.theme.screens.property.ContactLandlord

@Composable
fun AppNavHost(modifier: Modifier = Modifier, navController: NavHostController = rememberNavController(), startDestination:String= ROUTE_HOME, paddingValues: PaddingValues, sharedViewModel: SharedViewModel) {
    val userId = sharedViewModel.userId.observeAsState()
    Log.println(Log.INFO, "UserId Nav Check", userId.value.toString())

    NavHost(
        navController = navController,
        modifier = modifier,
        startDestination = startDestination
    ) {
        composable(ROUTE_LOGIN) {
            Loginscreen(navController, sharedViewModel)
        }
        composable(ROUTE_REGISTER) {
            RegisterScreen(navController)

        }

        composable(ROUTE_HOME) {
            HomeScreen(navController)

        }
        composable(ROUTE_ADD_PROFILE) {
            AddProfile(navController)
        }


        composable(ROUTE_VIEW_PROFILE) {
            Viewprofile(navController)

        }
        composable(ROUTE_UPDATE_PROFILE + "/{id}") { passedData ->
            Updateprofile(navController, passedData.arguments?.getString("id")!!)
        }
        composable(ROUTE_CONTACTUS) {
            ContactUs(navController)
        }
//        composable(ROUTE_BOOKING){
//            BookingScreen(navController)
//        }
        composable(ROUTE_NEWLOGIN) {
            NewLoginScreen(navController)
        }
        composable(ROUTE_NEWREGISTER) {
            NewRegisterScreen(navController)
        }
        composable(ROUTE_ALL_LISTINGS) {
            // Extracting the argument
            Box(modifier = Modifier.padding(paddingValues)) {
                AllListings(navController, sharedViewModel)
            }
        }

        composable(ROUTE_MY_LISTINGS) {
            Box(modifier = Modifier.padding(paddingValues)) {
                MyListings(navController, sharedViewModel)
            }
        }
        composable(ROUTE_USER_PROFILE) {
            Box(modifier = Modifier.padding(paddingValues)) {


            UserProfile(
                navController = navController,
                sharedViewModel = sharedViewModel
            )
        }}


        composable(ROUTE_DETAILED_PROPERTY+ "/{id}") {
//            backStackEntry ->
//            val propertyDetailsString = backStackEntry.arguments?.getString("propertyDetails")
//            val propertyDetails = Gson().fromJson(propertyDetailsString, PropertyDetails::class.java)


            val houseentity = HouseEntity( address = "111 Main St", price = 234, ownerId = 23, lease = "April 1st 2022", images =
            R.drawable.img_1 .toString(), description = "A 2 bedroom" )

            Box(modifier = Modifier.padding(paddingValues)) {
                DetailedProperty(navController, houseentity)
            }
        }

        composable(ROUTE_ADD_PROPERTY) {
            Box(modifier = Modifier.padding(paddingValues)) {
                AddProperty(navController, sharedViewModel)
            }
        }
        composable(ROUTE_CONTACT_LANDLORD){
            val propertyDetails =  HouseEntity( address = "111 Main St", price = 234, ownerId = 23, lease = "April 1st 2022", images =
            R.drawable.img_2.toString(), description = "A 2 bedroom" )
            Box(modifier = Modifier.padding(paddingValues)) {
                ContactLandlord(navController = navController, houseEntity = propertyDetails)
            }
        }
    }
}
