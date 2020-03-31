package com.example.inbook.domain

import android.app.Activity
import android.content.Context
import android.net.Uri
import android.util.Log
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Authentication(var mAuth: FirebaseAuth)  {

    private  var database: FirebaseDatabase = FirebaseDatabase.getInstance()
    var myRef: DatabaseReference = database.reference

     fun createAccount(email: String, password: String): String {
        Log.d(TAG, "createAccount:$email")
         var result: String =  ""
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {

                    val user: FirebaseUser? = mAuth.currentUser
                    myRef.child("user").child(user?.uid.toString()).setValue(user)

                    Log.d(TAG, "createUserWithEmail:success")
                    result = "success"
                } else {
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    result = "failed"
                }
            }
         return result
    }

     fun signIn(email: String, password: String):String {
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

    fun signOut() {
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

    fun signInWithGoogle(acct: GoogleSignInAccount): String{
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.id!!)
        var result: String =  ""
        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
        mAuth.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {

                    val user: FirebaseUser? = mAuth.currentUser
                    myRef.child("user").child(user?.uid.toString()).setValue(user)

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
