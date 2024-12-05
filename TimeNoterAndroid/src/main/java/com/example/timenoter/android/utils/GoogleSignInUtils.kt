package com.example.timenoter.android.utils

import android.content.Context
import android.content.Intent
import android.provider.Settings
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.result.ActivityResult
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import androidx.credentials.exceptions.GetCredentialException
import androidx.credentials.exceptions.NoCredentialException
import com.example.timenoter.android.R
import com.example.timenoter.android.data.events.AuthUIEvent
import com.example.timenoter.android.viewModels.AuthViewModel
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.security.MessageDigest
import java.util.UUID

class GoogleSignInUtils {

    fun doGoogleSignIn(
        authViewModel: AuthViewModel,
        coroutineScope: CoroutineScope,
        context: Context,
        startAddAccountIntentLauncher: ManagedActivityResultLauncher<Intent, ActivityResult>?,
    ) {
        //1. Instantiate the CredentialManager
        val credentialManager = CredentialManager.create(context)

        //3. Instantiate a GetCredentialRequest, then add the previously created googleIdOption using addCredentialOption() to retrieve the credentials.
        val googleSignRequest: GetCredentialRequest = GetCredentialRequest.Builder()
            .addCredentialOption(getGoogleIdOption(context))
            .build()

        coroutineScope.launch {
            try {
                // 4. Pass this request to getCredential() (Kotlin)
                // or getCredentialAsync() (Java) call to retrieve the user's available credentials.
                //Note: If the user has not signed in with any google account on the device, a NoCredentialException will be thrown.
                val result = credentialManager.getCredential(
                    request = googleSignRequest,
                    context = context,
                )
                //5. Pass the response (GetCredentialResponse) to ViewModel to handle the authentication process.
                authViewModel.onEvent(AuthUIEvent.HandleSignInResult(result))
            } catch (e: NoCredentialException) {
                e.printStackTrace()
                //6. This is a workaround for handling the NoCredentialException. Here, I handle it by lunching the intent for adding google account to the device.
                // Once the account has been added to the device, recall doGoogleSignIn() method.
                // if there is no credential, request to add google account
                startAddAccountIntentLauncher?.launch(getAddGoogleAccountIntent())
            } catch (e: GetCredentialException) {
                e.printStackTrace()
            }
        }
    }

    private fun getGoogleIdOption(context: Context): GetGoogleIdOption {
        //2.1 Generate the nonce to improve sign-in security and avoid replay attack.
        val rawNonce = UUID.randomUUID().toString()
        val bytes = rawNonce.toByteArray()
        val md = MessageDigest.getInstance("SHA-256")
        val digest = md.digest(bytes)
        val hashedNonce = digest.fold("") { str, it ->
            str + "%02x".format(it)
        }

        //2.2 Build the GetGoogleIdOption using its Builder function.
        return GetGoogleIdOption.Builder()
            .setFilterByAuthorizedAccounts(false) // true - check if the user has any accounts that have previously been used to sign in to the app
            .setServerClientId(context.getString(R.string.web_client_id))
            .setAutoSelectEnabled(true) // true- Enable automatic sign-in for returning users
            .setNonce(hashedNonce)
            .build()
    }

    //Helper function for creating GetGoogleIdOption.
    private fun getAddGoogleAccountIntent(): Intent {
        val intent = Intent(Settings.ACTION_ADD_ACCOUNT)
        intent.putExtra(Settings.EXTRA_ACCOUNT_TYPES, arrayOf("com.google"))
        return intent
    }
}