package com.example.ecommerceapp.presentation.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.ecommerceapp.presentation.ui.authentication.AuthScreen
import com.example.ecommerceapp.presentation.ui.home.HomeScreen
import com.google.firebase.auth.FirebaseAuth

@Composable
fun MyAppNavigation(
    navController: NavHostController,
) {

    val startDestination: String =
        if (FirebaseAuth.getInstance().currentUser == null) NavDestinations.Authentication.route else NavDestinations.Home.route
    NavHost(navController = navController, startDestination = startDestination) {
        composable(NavDestinations.Authentication.route) {
            AuthScreen(navController)
        }
        composable(NavDestinations.Home.route) {
            val user = FirebaseAuth.getInstance().currentUser
            user?.let { HomeScreen(it, navController) }
        }
    }
}
