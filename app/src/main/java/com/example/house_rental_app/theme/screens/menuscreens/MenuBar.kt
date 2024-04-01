package com.example.house_rental_app.theme.screens.menuscreens


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.house_rental_app.R
import com.example.house_rental_app.navigation.ROUTE_ADD_PROPERTY
import com.example.house_rental_app.navigation.ROUTE_ALL_LISTINGS
import com.example.house_rental_app.navigation.ROUTE_HOME
import com.example.house_rental_app.navigation.ROUTE_MY_LISTINGS
import com.example.house_rental_app.navigation.ROUTE_USER_PROFILE


@Composable
fun MenuBar(navController: NavController, currentRoute: String) {
    // Icon colors
    val activeColor = MaterialTheme.colorScheme.primary // Active icon color
    val inactiveColor = MaterialTheme.colorScheme.inversePrimary // Inactive icon color

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // Define icons with their routes and content descriptions
        val icons = listOf(
            Pair(ROUTE_ALL_LISTINGS, Icons.Default.Home) to "Listings",
            Pair(ROUTE_MY_LISTINGS, Icons.Default.LocationOn) to "My Listing",
            Pair(ROUTE_ADD_PROPERTY, Icons.Default.Add) to "Add Listing",
            Pair(ROUTE_USER_PROFILE, Icons.Default.Person) to "Profile",
            Pair(ROUTE_HOME, Icons.Default.ExitToApp) to "Logout" // Adjust based on your logout logic
        )

        icons.forEach { (routeIcon, contentDescription) ->
            val (route, icon) = routeIcon
            IconButton(onClick = { navController.navigate(route) }) {
                Icon(
                    icon,
                    contentDescription = contentDescription,
                    tint = if (currentRoute == route) activeColor else inactiveColor,
                    modifier = Modifier.size(28.dp)
                )
            }
        }
    }
}

@Composable
fun getCurrentRoute(navController: NavController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}



@Preview(showBackground = true)
@Composable
fun MenuBarPreview() {
    val navController = rememberNavController()
    val currentRoute = getCurrentRoute(navController) ?: ""
    MenuBar(navController = navController, currentRoute = currentRoute)
}
