package com.example.timenoter.android.login

import androidx.credentials.GetCredentialResponse

sealed class AuthUIEvent {
    data class SignUp(val email: String, val password: String) : AuthUIEvent()
    data class SignIn(val email: String, val password: String) : AuthUIEvent()
    data object SignOut : AuthUIEvent()

    data class HandleSignInResult(val result: GetCredentialResponse) : AuthUIEvent()
}