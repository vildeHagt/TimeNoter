package com.example.timenoter.android.data.events

import androidx.credentials.GetCredentialResponse
import com.google.firebase.auth.FirebaseUser

data class AuthUiState(
    val alreadySignUp: Boolean = false,
    val isLoading: Boolean = false,
    val user: FirebaseUser? = null,
    val isAnonymous: Boolean = false,
    val isAuthenticated: Boolean = false,
    val authState: AuthState = AuthState.Unauthenticated
)

enum class AuthState {
    Unauthenticated,
    Authenticated,
    SignedIn
}

sealed class AuthUIEvent {
    data class HandleSignInResult(val result: GetCredentialResponse) : AuthUIEvent()
}

