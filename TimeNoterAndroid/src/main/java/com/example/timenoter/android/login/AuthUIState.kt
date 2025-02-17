package com.example.timenoter.android.login

import com.google.firebase.auth.FirebaseUser

data class AuthUiState(
    val alreadySignUp: Boolean = false,
    val isLoading: Boolean = false,
    val user: FirebaseUser? = null,
    val isAuthenticated: Boolean = false,
    val authState: AuthState = AuthState.Unauthenticated
)

enum class AuthState {
    Unauthenticated,
    Authenticated,
    SignedIn
}

