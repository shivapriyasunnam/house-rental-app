package com.example.house_rental_app.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

import com.example.house_rental_app.data.PropertyDetails
import com.example.house_rental_app.models.User
import com.example.house_rental_app.theme.screens.Register.NewRegisterScreen
import com.example.house_rental_app.theme.screens.Register.RegisterScreen
import com.example.house_rental_app.theme.screens.booking.BookingScreen
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
import com.google.gson.Gson
import com.example.house_rental_app.theme.screens.profiless.Viewprofile

@Composable
fun AppNavHost(modifier: Modifier = Modifier, navController: NavHostController = rememberNavController(), startDestination:String= ROUTE_HOME) {

    NavHost(
        navController = navController,
        modifier = modifier,
        startDestination = startDestination
    ) {
        composable(ROUTE_LOGIN) {
            Loginscreen(navController)
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
        composable(ROUTE_CONTACTUS){
            ContactUs(navController)
        }
//        composable(ROUTE_BOOKING){
//            BookingScreen(navController)
//        }
        composable(ROUTE_NEWLOGIN){
            NewLoginScreen(navController)
        }
        composable(ROUTE_NEWREGISTER){
            NewRegisterScreen(navController)
        }
        composable(ROUTE_ALL_LISTINGS){
            AllListings(navController)
        }

        composable(ROUTE_MY_LISTINGS){
            MyListings(navController)
        }
        composable(ROUTE_USER_PROFILE) {
            val navController = rememberNavController()
            val user = User("example@example.com", "password123", "12345")

            UserProfile(
                user = user,
                onUserUpdated = { /* Handle user update logic here */ },
                navController = navController
            )
        }


        composable(ROUTE_DETAILED_PROPERTY) { backStackEntry ->
            val propertyDetailsString = backStackEntry.arguments?.getString("propertyDetails")
            val propertyDetails = Gson().fromJson(propertyDetailsString, PropertyDetails::class.java)
            DetailedProperty(navController, propertyDetails)
        }

        composable(ROUTE_ADD_PROPERTY){
            AddProperty(onPropertyAdded = {}, navController = navController )
        }
    }
}
