package com.example.house_rental_app.theme.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

import  com.example.house_rental_app.navigation.ROUTE_ADD_PROFILE
import com.example.house_rental_app.navigation.ROUTE_BOOKING
import com.example.house_rental_app.navigation.ROUTE_LOGIN
import com.example.house_rental_app.navigation.ROUTE_REGISTER
import com.example.house_rental_app.R

@Composable
fun HomeScreen(navController: NavHostController) {
    Column {
        Text(
            text = "Nish Rents",
            modifier = Modifier.padding(20.dp).align(Alignment.CenterHorizontally),
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold, color = Color.Black,
            fontFamily = FontFamily.Monospace,

            )
        Button(
            onClick = {
                navController.navigate(ROUTE_LOGIN)
            },
            colors = ButtonDefaults.buttonColors(Color.Black),
            modifier = Modifier.fillMaxWidth().padding(horizontal = 50.dp)
        )
        {
            Text(text = "Looking for a Home?")

        }
        Button(
            onClick = {
                navController.navigate(ROUTE_LOGIN)
            },
            colors = ButtonDefaults.buttonColors(Color.Black),
            modifier = Modifier.fillMaxWidth().padding(horizontal = 50.dp)
        )
        {
            Text(text = "Landlord's Login/Signup")

        }
//        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text = "Rent a house of your dreams or find the perfect tenant, join our app now!",
            modifier = Modifier.padding(20.dp).align(Alignment.CenterHorizontally),
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold, color = Color.Black,
            fontFamily = FontFamily.Monospace,
            textAlign = TextAlign.Center
            )

        Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
            Row(modifier = Modifier.padding(start = 5.dp)) {
                Card() {
                    Column {
                        Box(
                            modifier = Modifier
                                .height(150.dp)
                                .width(180.dp),

                            ) {
                            Image(
                                painter = painterResource(id = R.drawable.rentalten),
                                contentDescription = "",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .fillMaxHeight()
                                    .size(400.dp)
                            )
                        }
                        Text(
                            text = "20 Highland Dr",
                            textAlign = TextAlign.Center,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.Black
                        )
                    }


                }
                Spacer(modifier = Modifier.width(10.dp))
                Card() {
                    Column {
                        Box(
                            modifier = Modifier
                                .height(150.dp)
                                .width(180.dp),

                            ) {
                            Image(
                                painter = painterResource(id = R.drawable.rentalfour),
                                contentDescription = "",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .fillMaxHeight()
                                    .size(400.dp)
                            )
                        }
                        Text(
                            text = "163 Hawthorne St",
                            textAlign = TextAlign.Center,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.Black
                        )
                    }


                }

            }
            Spacer(modifier = Modifier.height(15.dp))
            Row(modifier = Modifier.padding(start = 5.dp)) {
                Card() {
                    Column {
                        Box(
                            modifier = Modifier
                                .height(150.dp)
                                .width(180.dp),

                            ) {
                            Image(
                                painter = painterResource(id = R.drawable.rentalone),
                                contentDescription = "",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .fillMaxHeight()
                                    .size(400.dp)
                            )
                        }
                        Text(
                            text = "3 Church St",
                            textAlign = TextAlign.Center,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.Black
                        )
                    }

                }


                Spacer(modifier = Modifier.width(10.dp))
                Card() {
                    Column {
                        Box(
                            modifier = Modifier
                                .height(150.dp)
                                .width(180.dp),

                            ) {
                            Image(
                                painter = painterResource(id = R.drawable.rentaltwo),
                                contentDescription = "",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .fillMaxHeight()
                                    .size(400.dp)
                            )
                        }
                        Text(
                            text = "40 Old Highway Rd",
                            textAlign = TextAlign.Center,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.Black
                        )
                    }

                }

            }
            Spacer(modifier = Modifier.width(10.dp))
            Row(modifier = Modifier.padding(start = 5.dp)) {
                Card() {
                    Column {
                        Box(
                            modifier = Modifier
                                .height(150.dp)
                                .width(180.dp),

                            ) {
                            Image(
                                painter = painterResource(id = R.drawable.rentalthree),
                                contentDescription = "",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .fillMaxHeight()
                                    .size(400.dp)
                            )
                        }
                        Text(
                            text = "45 University Av",
                            textAlign = TextAlign.Center,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.Black
                        )
                    }

                }


                Spacer(modifier = Modifier.width(10.dp))
                Card() {
                    Column {
                        Box(
                            modifier = Modifier
                                .height(150.dp)
                                .width(180.dp),

                            ) {
                            Image(
                                painter = painterResource(id = R.drawable.rentalfour),
                                contentDescription = "",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .fillMaxHeight()
                                    .size(400.dp)
                            )
                        }
                        Text(
                            text = "221 Scarboro Dr",
                            textAlign = TextAlign.Center,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.Black
                        )
                    }

                }

            }
            Spacer(modifier = Modifier.width(10.dp))
            Row(modifier = Modifier.padding(start = 5.dp)) {
                Card() {
                    Column {
                        Box(
                            modifier = Modifier
                                .height(150.dp)
                                .width(180.dp),

                            ) {
                            Image(
                                painter = painterResource(id = R.drawable.rentalfive),
                                contentDescription = "",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .fillMaxHeight()
                                    .size(400.dp)
                            )
                        }
                        Text(
                            text = "33 St Ninians St",
                            textAlign = TextAlign.Center,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.Black
                        )
                    }

                }


                Spacer(modifier = Modifier.width(10.dp))
                Card() {
                    Column {
                        Box(
                            modifier = Modifier
                                .height(150.dp)
                                .width(180.dp),

                            ) {
                            Image(
                                painter = painterResource(id = R.drawable.rentalsix),
                                contentDescription = "",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .fillMaxHeight()
                                    .size(400.dp)
                            )
                        }
                        Text(
                            text = "72 Arbor Dr",
                            textAlign = TextAlign.Center,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.Black
                        )
                    }

                }

            }
            Spacer(modifier = Modifier.width(10.dp))
            Row(modifier = Modifier.padding(start = 5.dp)) {
                Card() {
                    Column {
                        Box(
                            modifier = Modifier
                                .height(150.dp)
                                .width(180.dp),

                            ) {
                            Image(
                                painter = painterResource(id = R.drawable.rentalseven),
                                contentDescription = "",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .fillMaxHeight()
                                    .size(400.dp)
                            )
                        }
                        Text(
                            text = "93 Kirk St",
                            textAlign = TextAlign.Center,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.Black
                        )
                    }

                }


                Spacer(modifier = Modifier.width(10.dp))
                Card() {
                    Column {
                        Box(
                            modifier = Modifier
                                .height(150.dp)
                                .width(180.dp),

                            ) {
                            Image(
                                painter = painterResource(id = R.drawable.rentaleight),
                                contentDescription = "",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .fillMaxHeight()
                                    .size(400.dp)
                            )
                        }
                        Text(
                            text = "2 Mackinon St",
                            textAlign = TextAlign.Center,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.Black
                        )
                    }

                }

            }
        }
    }
}

//
//
//
//
//                Spacer(modifier = Modifier.width(10.dp))
////                Row(modifier = Modifier.padding(start = 5.dp)) {
//                Card() {
//                    Column {
//                        Box(
//                            modifier = Modifier
//                                .height(150.dp)
//                                .width(180.dp),
//
//                            ) {
//                            Image(
//                                painter = painterResource(id = R.drawable.rentalfive),
//                                contentDescription = "",
//                                contentScale = ContentScale.Crop,
//                                modifier = Modifier
//                                    .fillMaxWidth()
//                                    .fillMaxHeight()
//                                    .size(400.dp)
//                            )
//                        }
//                        Text(
//                            text = "Starehe Rentals",
//                            textAlign = TextAlign.Center,
//                            fontSize = 20.sp,
//                            fontWeight = FontWeight.SemiBold,
//                            color = Color.Black
//                        )
//                    }
//
//                }
//
//
//                Spacer(modifier = Modifier.width(10.dp))
////                Row(modifier = Modifier.padding(start = 5.dp)) {
//                Card() {
//                    Column {
//                        Box(
//                            modifier = Modifier
//                                .height(150.dp)
//                                .width(180.dp),
//
//                            ) {
//                            Image(
//                                painter = painterResource(id = R.drawable.rentalsix),
//                                contentDescription = "",
//                                contentScale = ContentScale.Crop,
//                                modifier = Modifier
//                                    .fillMaxWidth()
//                                    .fillMaxHeight()
//                                    .size(400.dp)
//                            )
//                        }
//                        Text(
//                            text = "Fully Furnished",
//                            textAlign = TextAlign.Center,
//                            fontSize = 20.sp,
//                            fontWeight = FontWeight.SemiBold,
//                            color = Color.Black
//                        )
//                    }
//
//                }
//            }
//
//
//            Spacer(modifier = Modifier.width(10.dp))
//            Card() {
//                Column {
//                    Box(
//                        modifier = Modifier
//                            .height(150.dp)
//                            .width(180.dp),
//
//                        ) {
//                        Image(
//                            painter = painterResource(id = R.drawable.rentalseven),
//                            contentDescription = "",
//                            contentScale = ContentScale.Crop,
//                            modifier = Modifier
//                                .fillMaxWidth()
//                                .fillMaxHeight()
//                                .size(400.dp)
//                        )
//                    }
//                    Text(
//                        text = "Great Interior Design",
//                        textAlign = TextAlign.Center,
//                        fontSize = 20.sp,
//                        fontWeight = FontWeight.SemiBold,
//                        color = Color.Black
//                    )
//                }
//
//            }
//
//        }
//    }
//    Spacer(modifier = Modifier.width(10.dp))
//
////    Card() {
////        Column {
////            Box(
////                modifier = Modifier
////                    .height(150.dp)
////                    .width(180.dp),
////
////                ) {
////                Image(
////                    painter = painterResource(id = R.drawable.rentaleight),
////                    contentDescription = "",
////                    contentScale = ContentScale.Crop,
////                    modifier = Modifier
////                        .fillMaxWidth()
////                        .fillMaxHeight()
////                        .size(400.dp)
////                )
////            }
////            Text(
////                text = "Efficient Lighting",
////                textAlign = TextAlign.Center,
////                fontSize = 20.sp,
////                fontWeight = FontWeight.SemiBold,
////                color = Color.Black
////            )
////        }
////
//
//
//    Spacer(modifier = Modifier.width(10.dp))
//
//    Button(
//        onClick = {
//            navController.navigate(ROUTE_LOGIN)
//        },
//
//        colors = ButtonDefaults.buttonColors(Color.Black),
//        modifier = Modifier.fillMaxWidth()
//    )
//    {
//        Text(text = "Already a member?Click to Login")
//
//    }


//        Button(
//            onClick = {
//                navController.navigate(ROUTE_BOOKING)
//            },
//            colors = ButtonDefaults.buttonColors(Color.Black),
//            modifier = Modifier.fillMaxWidth()
//        )
//        {
//            Text(text = "Book with us a beautiful home from any high-end estates ")
//
//        }











@Preview
@Composable
fun HomeScreenPreview(){
    HomeScreen(rememberNavController())

//    CustomAppBar()
}


