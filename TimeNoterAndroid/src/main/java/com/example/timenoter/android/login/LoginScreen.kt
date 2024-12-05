package com.example.timenoter.android.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import com.example.timenoter.android.R
import com.example.timenoter.android.components.NoterButton
import com.example.timenoter.android.components.NoterText
import com.example.timenoter.android.theme.TimeColors

@Preview
@Composable
fun LoginScreenPreview() {
    LoginScreen()
}

@Composable
fun LoginScreen() {
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
            //Sign in with Google
        }

        NoterText("or", fontSize = 4.em )

        NoterButton(
            title = stringResource(id = R.string.signInGoogle),
            modifier = Modifier.width(250.dp),
            fontSize = 4.em
        ) {
            //Sign in with Email
        }
    }
}