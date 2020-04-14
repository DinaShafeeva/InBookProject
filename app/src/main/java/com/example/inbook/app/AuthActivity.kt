package com.example.inbook.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.inbook.R
import com.example.inbook.app.mybooks.MyBooksFragment
import com.example.inbook.data.authentication.AuthenticationImpl
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_auth.*


class AuthActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        val authentication: AuthenticationImpl =
            AuthenticationImpl()

        val btn_reg = btn_register.setOnClickListener() {x ->
            if(
            authentication.createAccount(et_email.text.toString(), et_password.text.toString())
                .equals("success")
            )
            openFragment()
        }
        val btn_auth = btn_auth.setOnClickListener() { x ->
            if (
                authentication.signIn(et_email.text.toString(), et_password.text.toString())
                    .equals("success")
            )
                openFragment()
        }
        val btn_google_sign_in = btn_google_sign_in.setOnClickListener(){ x ->
            if(
                authentication.signInWithGoogle(getString(R.string.default_web_client_id))
                    .equals("success")
            )
                openFragment()
        }
        Snackbar.make(findViewById(android.R.id.content),"Failed", Snackbar.LENGTH_LONG).show()
    }

    private fun openFragment() {
        val fragment =
            MyBooksFragment.newInstance()
        val transaction = supportFragmentManager.beginTransaction().apply { }
        transaction.replace(R.id.container_main, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

}
