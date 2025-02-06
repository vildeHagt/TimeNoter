package com.example.timenoter.android.login

import androidx.credentials.GetCredentialResponse

sealed class AuthUIEvent {
    data class HandleSignInResult(val result: GetCredentialResponse) : AuthUIEvent()
}