package com.example.inbook.domain.authentication

import android.view.View


interface Authentication {
    fun createAccount(email: String, password: String,  view:View): String
    fun signIn(email: String, password: String, view: View): String
    fun signOut()
    fun isAuth(): Boolean
    fun getName(): String
}
