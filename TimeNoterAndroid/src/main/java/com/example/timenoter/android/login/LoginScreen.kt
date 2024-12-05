package com.example.timenoter.android.login

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.navigation.NavHostController
import com.example.timenoter.android.R
import com.example.timenoter.android.components.NoterButton
import com.example.timenoter.android.components.NoterText
import com.example.timenoter.android.theme.TimeColors
import com.example.timenoter.android.utils.GoogleSignInUtils
import com.example.timenoter.android.viewModels.AuthViewModel

@Preview
@Composable
fun LoginScreenPreview() {
    val context = LocalContext.current
    val navController = NavHostController(context)
    LoginScreen(navController)
}

@Composable
fun LoginScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(5.dp)
            .background(TimeColors.Basics.backgroundShadow),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        NoterButton(
            title = stringResource(id = R.string.signIn),
            modifier = Modifier.width(250.dp),
            fontSize = 4.em
        ) {
            //Sign in with Email
        }

        NoterText("or", fontSize = 4.em )

        SignInWithGoogleButton(navController)
    }
}

@Composable
private fun SignInWithGoogleButton(navController: NavHostController) {
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    val authViewModel = AuthViewModel()
    val googleSignInUtils = GoogleSignInUtils()

    LaunchedEffect(authViewModel) {
        authViewModel.onNavigationRequested = {
            // Trigger navigation to the TimeMenu screen
            navController.navigate("TimeMenu")
        }
    }

   //1. Instantiate the intent launcher for handling the process of adding a Google account and to re-trigger the sign-in process once the account adding process is complete.
    val startAddAccountIntentLauncher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()) {
            // Once the account has been added, do sign in again.
            googleSignInUtils.doGoogleSignIn(authViewModel, coroutineScope, context, null)
        }

    NoterButton(
        title = stringResource(id = R.string.signInGoogle),
        modifier = Modifier.width(250.dp),
        fontSize = 4.em
    ) {
        googleSignInUtils.doGoogleSignIn(
            authViewModel,
            coroutineScope,
            context,
            startAddAccountIntentLauncher
        )
    }
}