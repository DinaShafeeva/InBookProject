package com.example.inbook.app.authentication.vm

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.inbook.data.authentication.AuthenticationImpl
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult

class AuthViewModel(val authentication: AuthenticationImpl): ViewModel() {

    fun isAuth(): Boolean {
        Log.d("IsAuthVM", authentication.isAuth().toString())
        return authentication.isAuth()
    }

    fun signIn(email:String, password:String, view:View):String {
        return authentication.signIn(email, password, view)
    }

    fun createAccount(email: String, password: String, view:View): String{
        return authentication.createAccount(email, password, view)
    }
}
