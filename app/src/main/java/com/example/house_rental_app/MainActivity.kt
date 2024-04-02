    package com.example.house_rental_app

    import android.os.Bundle
    import android.util.Log
    import androidx.activity.ComponentActivity
    import androidx.activity.compose.setContent
    import androidx.compose.material3.ExperimentalMaterial3Api
    import androidx.compose.material3.Scaffold
    import androidx.compose.runtime.Composable
    import androidx.compose.runtime.livedata.observeAsState
    import androidx.lifecycle.viewmodel.compose.viewModel
    import androidx.navigation.compose.rememberNavController
    import com.example.house_rental_app.data.SharedViewModel
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
        val sharedViewModel: SharedViewModel = viewModel() // Scoped to Activity
        val userId = sharedViewModel.userId.observeAsState()
        Log.println(Log.INFO,"In Main", userId.toString())

        Log.println(Log.INFO,"In Main", sharedViewModel.userId.value.toString())
        Scaffold(
            topBar = {
                if (currentRoute != "home" && currentRoute != "login" && currentRoute!="register") {
                    MenuBar(navController = navController, currentRoute = currentRoute, sharedViewModel = sharedViewModel)
                }
            }
        ) { paddingValues ->
            AppNavHost(
                navController = navController,
                paddingValues = paddingValues, // Pass the padding values to AppNavHost
                sharedViewModel = sharedViewModel
            )
        }
    }
