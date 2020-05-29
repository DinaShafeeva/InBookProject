package com.example.inbook.app.authentication.vm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.inbook.data.authentication.AuthenticationImpl

class AuthViewModel(val authentication: AuthenticationImpl): ViewModel() {

    fun isAuth(): Boolean {
        Log.d("IsAuthVM", authentication.isAuth().toString())
        return authentication.isAuth()
    }

    fun signIn(email:String, password:String): String{
        return authentication.signIn(email, password)
    }

    fun createAccount(email: String, password: String): String{
        return authentication.createAccount(email, password)
    }

    fun signInWithGoogle(string: String): String{
        return authentication.signInWithGoogle(string)
    }
}