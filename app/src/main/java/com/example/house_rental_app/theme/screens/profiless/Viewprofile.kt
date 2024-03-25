package com.example.house_rental_app.theme.screens.profiless

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.house_rental_app.data.profileviewmodel
import com.example.house_rental_app.models.Profile
import com.example.house_rental_app.navigation.ROUTE_UPDATE_PROFILE

@Composable
fun Viewprofile(navController:NavHostController) {
    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally) {



        var context = LocalContext.current
        var profileRepository = profileviewmodel(navController, context)


        val emptyProductState = remember { mutableStateOf(Profile("","","","")) }
        var emptyProfilesListState = remember { mutableStateListOf<Profile>() }

        var Profiles = profileRepository.viewProfiles(emptyProductState, emptyProfilesListState)


        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "All profiles",
                fontSize = 30.sp,
                fontFamily = FontFamily.Cursive,
                color = Color.Black)

            Spacer(modifier = Modifier.height(20.dp))
            LazyColumn(){
                items(Profiles){
                    ProfileItem(
                        name = it.name,
                        housenumber = it.housenumber,
                        contact = it.contact,
                        id = it.id,
                        navController = navController ,
                        profileRepository= profileRepository
                    )
                }
            }





//
                }
            }
        }




@Composable
fun ProfileItem(name:String, housenumber:String, contact:String, id:String,
                navController:NavHostController, profileRepository: profileviewmodel
) {



    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = name)
        Text(text = housenumber)
        Text(text = contact)
        Button(onClick = {
            profileRepository.deleteProfile(id)
        },
            colors = ButtonDefaults.buttonColors(Color.Black),
            modifier = Modifier.fillMaxWidth()) {
            Text(text = "Delete")
        }
        Button(onClick = {
            navController.navigate(ROUTE_UPDATE_PROFILE+"/$id")
        },colors = ButtonDefaults.buttonColors(Color.Black),
            modifier = Modifier.fillMaxWidth()
            ) {
            Text(text = "Update")
        }
    }

}


@Preview
@Composable
fun Preview(){
    Viewprofile(rememberNavController())
}