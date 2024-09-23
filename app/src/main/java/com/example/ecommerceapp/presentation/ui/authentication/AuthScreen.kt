package com.example.ecommerceapp.presentation.ui.authentication

import android.app.Activity
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.e_commertceapp.R
import com.example.ecommerceapp.presentation.ui.navigation.NavDestinations
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse

val providers = arrayListOf(
    AuthUI.IdpConfig.AnonymousBuilder().build(),
    AuthUI.IdpConfig.EmailBuilder().build(),
    AuthUI.IdpConfig.GoogleBuilder().build(),
)

@Composable
fun AuthScreen(navController: NavController) {


    val signInLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                // Sign-in successful
                // Navigate to HomeScreen with user data
                navController.navigate(NavDestinations.Home.route) {
                    // Clear back stack if needed
                    popUpTo(NavDestinations.Authentication.route) { inclusive = true }
                }
            } else {
                // Handle sign-in failure
                val response = IdpResponse.fromResultIntent(result.data)
                Log.e("AuthScreen", "Sign-in failed: ${response?.error?.errorCode}")
                navController.navigate(NavDestinations.Authentication.route) {
                    // Clear back stack if needed
                    popUpTo(NavDestinations.Authentication.route) { inclusive = true }
                }
            }
        }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.shopping_basket),
            contentDescription = "Logo",
            modifier = Modifier.size(150.dp),
            contentScale = ContentScale.Fit,
        )
        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            val signInIntent = AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .setLogo(R.drawable.shopping_basket)
                .build()
            signInLauncher.launch(signInIntent)
        }) {


            Text("Sign In")
        }
    }
}
