package com.safar.mobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Flight
import androidx.compose.material.icons.filled.Hotel
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Business
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.runtime.getValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.safar.mobile.navigation.Screen
import com.safar.mobile.ui.screens.FlightsScreen
import com.safar.mobile.ui.screens.HomeScreen
import com.safar.mobile.ui.screens.HotelsScreen
import com.safar.mobile.ui.screens.LoginScreen
import com.safar.mobile.ui.screens.ProfileScreen
import com.safar.mobile.ui.screens.SplashScreen
import com.safar.mobile.ui.screens.ToursScreen
import com.safar.mobile.ui.screens.WelcomeScreen
import com.safar.mobile.ui.screens.AgencyListingScreen
import com.safar.mobile.ui.screens.AgencyProfileScreen
import com.safar.mobile.ui.screens.HelpScreen
import com.safar.mobile.ui.theme.SafarTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SafarTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SafarApp()
                }
            }
        }
    }
}

@Composable
fun SafarApp() {
    val homeViewModel: com.safar.mobile.viewmodel.HomeViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val mainTabs = listOf(
        Screen.Home,
        Screen.Flights,
        Screen.Agencies,
        Screen.Hotels,
        Screen.Tours,
        Screen.Profile
    )
    
    val showBottomBar = currentRoute in mainTabs.map { it.route }

    Scaffold(
        topBar = {
            if (showBottomBar) { // Show top bar only for main tabs
                val title = when (currentRoute) {
                    Screen.Home.route -> "SAFAR"
                    Screen.Flights.route -> "Flights"
                    Screen.Hotels.route -> "Hotels"
                    Screen.Tours.route -> "Tour Packages"
                    Screen.Profile.route -> "My Profile"
                    else -> "SAFAR"
                }
                @OptIn(ExperimentalMaterial3Api::class)
                TopAppBar(
                    title = { Text(title, fontWeight = FontWeight.Bold) },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = if (currentRoute == Screen.Home.route) com.safar.mobile.ui.theme.SafarPrimary else androidx.compose.ui.graphics.Color.White,
                        titleContentColor = if (currentRoute == Screen.Home.route) androidx.compose.ui.graphics.Color.White else androidx.compose.ui.graphics.Color.Black
                    ),
                    actions = {
                        if (currentRoute == Screen.Home.route) {
                            IconButton(onClick = {}) {
                                Icon(androidx.compose.material.icons.Icons.Default.Notifications, contentDescription = "Notifications", tint = androidx.compose.ui.graphics.Color.White)
                            }
                        }
                    }
                )
            }
        },
        bottomBar = {
            if (showBottomBar) {
                NavigationBar(containerColor = androidx.compose.ui.graphics.Color.White) {
                    val items = listOf("Home", "Flights", "Agencies", "Hotels", "Tours", "Profile")
                    val icons = listOf(
                        Icons.Filled.Home,
                        Icons.Filled.Flight,
                        Icons.Filled.Business,
                        Icons.Filled.Hotel,
                        Icons.Filled.Map,
                        Icons.Filled.Person
                    )
                    
                    items.forEachIndexed { index, item ->
                        val screen = mainTabs[index]
                        NavigationBarItem(
                            icon = { Icon(icons[index], contentDescription = item) },
                            label = { Text(item) },
                            selected = currentRoute == screen.route,
                            onClick = {
                                if (currentRoute != screen.route) {
                                    navController.navigate(screen.route) {
                                        popUpTo(Screen.Home.route) {
                                            saveState = true
                                        }
                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                }
                            }
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Splash.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Splash.route) {
                SplashScreen(navController = navController)
            }
            composable(Screen.Welcome.route) {
                WelcomeScreen(navController = navController)
            }
            composable(Screen.Login.route) {
                LoginScreen(navController = navController)
            }
            composable(Screen.Signup.route) {
                com.safar.mobile.ui.screens.SignupScreen(navController = navController)
            }
            composable(Screen.Home.route) {
                HomeScreen(
                    navController = navController,
                    viewModel = homeViewModel,
                    onTourClick = { navController.navigate(Screen.Tours.route) },
                    onHotelClick = { navController.navigate(Screen.Hotels.route) },
                    onFlightClick = { navController.navigate(Screen.Flights.route) }
                )
            }
            composable(Screen.Flights.route) {
                FlightsScreen(navController = navController)
            }
            composable(Screen.Hotels.route) {
                HotelsScreen(navController = navController)
            }
            composable(Screen.Tours.route) {
                ToursScreen(navController = navController)
            }
            composable(Screen.Profile.route) {
                ProfileScreen(navController = navController, viewModel = homeViewModel)
            }
            composable(
                route = Screen.DestinationDetails.route,
                arguments = listOf(androidx.navigation.navArgument("destinationId") { type = androidx.navigation.NavType.IntType })
            ) { backStackEntry ->
                val destinationId = backStackEntry.arguments?.getInt("destinationId") ?: 0
                com.safar.mobile.ui.screens.DestinationDetailsScreen(navController = navController, destinationId = destinationId)
            }
            composable(
                route = Screen.HostelDetails.route,
                arguments = listOf(androidx.navigation.navArgument("hostelId") { type = androidx.navigation.NavType.StringType })
            ) { backStackEntry ->
                val hostelId = backStackEntry.arguments?.getString("hostelId") ?: ""
                com.safar.mobile.ui.screens.HostelDetailsScreen(navController = navController, hostelId = hostelId)
            }
            composable(Screen.PaymentMethods.route) {
                com.safar.mobile.ui.screens.PaymentMethodsScreen(navController = navController)
            }
            composable(Screen.Agencies.route) {
                AgencyListingScreen(navController = navController)
            }
            composable(Screen.AgencyProfile.route) { backStackEntry ->
                val agencyId = backStackEntry.arguments?.getString("agencyId") ?: ""
                AgencyProfileScreen(navController = navController, agencyId = agencyId)
            }
            composable(Screen.Help.route) {
                HelpScreen(navController = navController)
            }
        }
    }
}