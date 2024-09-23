package com.example.ecommerceapp.presentation.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.ecommerceapp.presentation.ui.navigation.NavDestinations
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

@Composable
fun HomeScreen(user: FirebaseUser,navController: NavController) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center)
    {

        Column {
            val context=LocalContext.current.applicationContext
            Text("Welcome, ${if (user.isAnonymous) "Anonymous User" else user.displayName}")
            Button(onClick = {
                AuthUI.getInstance()
                    .signOut(context)
                    .addOnCompleteListener {
                        // User is now signed out
                        FirebaseAuth.getInstance().signOut()
                    }


                navController.navigate(NavDestinations.Authentication.route)
            }) {
                Text("Logout")

            }

        }
    }
}