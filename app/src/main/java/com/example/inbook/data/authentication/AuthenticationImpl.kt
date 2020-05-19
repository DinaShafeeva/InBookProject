package com.example.inbook.data.authentication

import android.app.Application
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import com.example.inbook.di.App
import com.example.inbook.domain.authentication.Authentication
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import javax.inject.Inject


class AuthenticationImpl @Inject constructor(val context: Context)
    : Authentication {

    var mAuth: FirebaseAuth = FirebaseAuth.getInstance()
    lateinit var googleSignInClient: GoogleSignInClient

    private  var database: FirebaseDatabase = FirebaseDatabase.getInstance()
    var myRef: DatabaseReference = database.reference

     override fun createAccount(email: String, password: String): String {
        Log.d(TAG, "createAccount:$email")
         var result: String =  ""
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user: FirebaseUser? = mAuth.currentUser
 //                   myRef.child("user").child(user?.uid.toString()).setValue(user)
                    Log.d(TAG, "createUserWithEmail:success")
                    result = "success"

                } else {
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    result = "failed"
                }
            }
         return result
    }

    override fun signIn(email: String, password: String):String {
        Log.d(TAG, "signIn:$email")
         var result: String =  ""
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "signInWithEmail:success")
                    result = "success"

                } else {
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                    result = "failed"
                }
            }
         return result
     }

    override fun signOut() {
        mAuth.signOut()
    }

    fun getCurrentUser(){
        val user = FirebaseAuth.getInstance().currentUser
        if (user != null) {
            val name = user.displayName
            val email = user.email
            val photoUrl: Uri? = user.photoUrl
            val emailVerified = user.isEmailVerified
            val uid = user.uid
        }
    }

    override fun signInWithGoogle(string: String): String{

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(string)
            .requestEmail()
            .build()
         googleSignInClient = GoogleSignIn.getClient(context, gso)

        val acct = GoogleSignIn.getSignedInAccountFromIntent(googleSignInClient.signInIntent)?.
            getResult(ApiException::class.java)

        Log.d(TAG, "firebaseAuthWithGoogle:" + acct?.id)
        var result: String =  ""
        val credential = GoogleAuthProvider.getCredential(acct?.idToken, null)
        mAuth.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user: FirebaseUser? = mAuth.currentUser
 //                   myRef.child("user").child(user?.uid.toString()).setValue(user)
                    print("все зашибись")
                    Log.d(TAG, "signInWithCredential:success")
                    result = "success"

                } else {
                    Log.w(TAG, "signInWithCredential:failure", task.exception)
                    result = "failed"
                }
            }
        return result
    }

    companion object {
        private const val TAG = "EmailPassword"
    }
}
