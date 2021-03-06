package com.raghad.goexplore.user

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.ErrorCodes
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.google.firebase.auth.FirebaseAuth
import com.raghad.goexplore.MainActivity
import com.raghad.goexplore.R

/*
user login page
 */
class LoginActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        var button = findViewById<Button>(R.id.Button)

        button.setOnClickListener {

            // Create and launch sign-in intent
            signInLauncher.launch(signInIntent)
            // [END auth_fui_create_intent] }

        }

    }

    private val signInLauncher = registerForActivityResult(
        FirebaseAuthUIActivityResultContract()
    ) { res ->
        this.onSignInResult(res)
    }

    val providers = arrayListOf(
        AuthUI.IdpConfig.EmailBuilder().build(),
        AuthUI.IdpConfig.GoogleBuilder().build()
    )

    val signInIntent = AuthUI.getInstance()
        .createSignInIntentBuilder()
        .setAvailableProviders(providers)
        .setTheme(R.style.raghad)
        .build()

    private fun onSignInResult(result: FirebaseAuthUIAuthenticationResult) {

        val response = result.idpResponse
        if (result.resultCode == AppCompatActivity.RESULT_OK) {
            // Successfully signed in
            val user = FirebaseAuth.getInstance().currentUser

            //open another activity to display home fragment
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

        } else {
            if (response == null){
                finish()
            }
            if (response?.error?.errorCode == ErrorCodes.NO_NETWORK){
                Toast.makeText(this,response?.error?.errorCode.toString(),Toast.LENGTH_LONG).show()
                return
            }
            if (response?.error?.errorCode == ErrorCodes.UNKNOWN_ERROR){
                Toast.makeText(this,response?.error?.errorCode.toString(),Toast.LENGTH_LONG).show()
                return
            }
        }
    }
}