package com.example.ecommerceapp.presentation.ui.navigation

sealed class NavDestinations(val route: String) {
    data object Home : NavDestinations("homePage")
    data object Authentication : NavDestinations("authenticationPage")

    }
