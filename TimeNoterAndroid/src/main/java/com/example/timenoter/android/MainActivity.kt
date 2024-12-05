package com.example.timenoter.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.timenoter.android.login.LoginScreen
import com.example.timenoter.android.views.TimeMenu
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            enableEdgeToEdge()

            val navController = rememberNavController()
            val startDestination = if (isLoggedIn()) "home" else "login"

            NavHost(navController = navController, startDestination = startDestination) {
                composable("login") {
                    LoginScreen(navController)
                }
                composable("home") {
                    TimeMenu(navController)
                }
            }
/*
            if (!isLoggedIn()) {
                LoginScreen()
            } else {
                MyApplicationTheme {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(TimeColors.Basics.background),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            TimeMenu()
                        }
                    }
                }
            }*/
        }
    }
}

fun isLoggedIn(): Boolean {
    val firebaseUser = Firebase.auth.currentUser
    return firebaseUser != null
}