package com.example.inbook.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.inbook.R
import com.example.inbook.domain.Authentication
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_auth.*


class AuthActivity : AppCompatActivity() {
    private lateinit var mAuth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        mAuth = FirebaseAuth.getInstance()

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(this, gso)

        val authentication: Authentication = Authentication(mAuth)
        val btn_reg = btn_register.setOnClickListener() {x ->
            authentication.createAccount(et_email.text.toString(), et_password.text.toString(), baseContext, this)}
        val btn_auth = btn_auth.setOnClickListener() {x ->
            authentication.signIn(et_email.text.toString(), et_password.text.toString(), baseContext, this)}
        val btn_google_sign_in = btn_google_sign_in.setOnClickListener(){ x ->
            authentication.signInWithGoogle(GoogleSignIn.getSignedInAccountFromIntent(intent)?.
                getResult(ApiException::class.java)!!,baseContext,this)
        }
    }

    public override fun onStart() {
        super.onStart()
        val currentUser = mAuth.currentUser
    }
}
