package com.example.inbook.domain.authentication


interface Authentication {
    fun createAccount(email: String, password: String): String
    fun signIn(email: String, password: String):String
    fun signInWithGoogle(string: String): String
    fun signOut()

}