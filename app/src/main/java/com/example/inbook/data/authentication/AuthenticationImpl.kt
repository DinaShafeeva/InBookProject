package com.example.inbook.data.authentication

import android.app.Application
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.View
import androidx.navigation.Navigation
import com.example.inbook.R
import com.example.inbook.di.App
import com.example.inbook.domain.authentication.Authentication
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import javax.inject.Inject


class AuthenticationImpl @Inject constructor(val context: Context)
    : Authentication {

    var mAuth: FirebaseAuth = FirebaseAuth.getInstance()

     override fun createAccount(email: String, password: String, view:View): String {
        Log.d(TAG, "createAccount:$email")
         var result: String =  ""
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user: FirebaseUser? = mAuth.currentUser
                    Log.d(TAG, "createUserWithEmail:success")
                    result = "success"
                    Navigation.findNavController(view).navigate(R.id.profileFragment)
                } else {
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    result = "failed"
                }
            }
         return result
    }

    override fun signIn(email: String, password: String, view: View):String {
        Log.d(TAG, "signIn:$email")
         var result: String =  ""
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "signInWithEmail:success")
                    result = "success"
                    Navigation.findNavController(view).navigate(R.id.profileFragment)
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

    override fun isAuth(): Boolean {
        if(mAuth.currentUser != null){
            return true
        }
        return false
    }

    override fun getName(): String {
        return mAuth.currentUser?.displayName ?: mAuth.currentUser?.email ?: "name"
    }

    companion object {
        private const val TAG = "EmailPassword"
    }
}
