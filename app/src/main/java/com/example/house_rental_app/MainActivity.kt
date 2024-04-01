    package com.example.house_rental_app

    import android.os.Bundle
    import androidx.activity.ComponentActivity
    import androidx.activity.compose.setContent
    import androidx.compose.material3.ExperimentalMaterial3Api
    import androidx.compose.material3.Scaffold
    import androidx.compose.runtime.Composable
    import androidx.navigation.compose.rememberNavController
    import com.example.house_rental_app.navigation.AppNavHost
    import com.example.house_rental_app.theme.screens.menuscreens.MenuBar
    import com.example.house_rental_app.theme.screens.menuscreens.getCurrentRoute

    class MainActivity : ComponentActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContent {
                MainAppStructure()

            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun MainAppStructure() {
        val navController = rememberNavController()
        val currentRoute = getCurrentRoute(navController) ?: ""

        Scaffold(
            topBar = {
                if (currentRoute != "home" && currentRoute != "login" && currentRoute!="register") {
                    MenuBar(navController = navController, currentRoute = currentRoute)
                }
            }
        ) { paddingValues ->
            AppNavHost(
                navController = navController,
                paddingValues = paddingValues // Pass the padding values to AppNavHost
            )
        }
    }
