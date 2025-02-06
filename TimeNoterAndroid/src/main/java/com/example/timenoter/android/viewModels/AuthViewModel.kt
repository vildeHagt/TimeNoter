package com.example.timenoter.android.viewModels

import android.util.Log
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialResponse
import com.example.timenoter.android.login.AuthState
import com.example.timenoter.android.login.AuthUiState
import com.example.timenoter.android.login.AuthUIEvent
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenParsingException
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class AuthViewModel: BaseViewModel<AuthUiState, AuthUIEvent>(AuthUiState()){
    var onNavigationRequested: (() -> Unit)? = null

    suspend fun handleSignIn(result: GetCredentialResponse) {
        //1. Once the API is successful, extract the CustomCredential
        //which holds the result for GoogleIdTokenCredential data.
        when (val credential = result.credential) {
            is CustomCredential -> {
                //2. The type for CustomCredential should be equal to the value of GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL.
                if (credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
                    try {
                        //3. Convert the object into a GoogleIdTokenCredential using the GoogleIdTokenCredential.createFrom method.
                        val googleIdTokenCredential = GoogleIdTokenCredential
                            .createFrom(credential.data)

                        //4. Extract the GoogleIdTokenCredential ID token string.
                        val googleIdToken = googleIdTokenCredential.idToken

                        //5. Using GoogleAuthProvider, generate AuthCredential for authentication user on firebase server.
                        val authCredential = GoogleAuthProvider.getCredential(googleIdToken, null)

                        //6. Authenticate user and retrieve user object.
                        val user = Firebase.auth.signInWithCredential(authCredential).await().user

                        //7. Go to TimeMenu screen
                        user?.run {
                            updateUiState {
                                AuthUiState(
                                    alreadySignUp = true,
                                    isLoading = false,
                                    user = user,
                                    isAuthenticated = true,
                                    authState = AuthState.SignedIn
                                )
                            }
                        }
                    } catch (e: GoogleIdTokenParsingException) {
                        Log.e("TAG", "Received an invalid google id token response", e)
                    } catch (e: Exception) {
                        Log.e("TAG", "Unexpected error")
                    }
                }
            }

            else -> {
                // Catch any unrecognized credential type here.
                Log.e("TAG", "Unexpected type of credential")
            }
        }
    }

    override suspend fun handleEvent(event: AuthUIEvent) {
        when (event) {
            is AuthUIEvent.HandleSignInResult -> handleSignIn(event.result)
        }
    }
}