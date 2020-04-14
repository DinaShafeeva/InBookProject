package com.example.inbook.app.authentication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.inbook.R
import com.example.inbook.data.authentication.AuthenticationImpl
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_auth.*

class AuthFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val authentication: AuthenticationImpl =
            AuthenticationImpl()

        val btn_reg = btn_register.setOnClickListener() { x ->
            if (
                authentication.createAccount(et_email.text.toString(), et_password.text.toString())
                    .equals("success")
            )
                view?.let { Navigation.findNavController(it).navigate(R.id.profileFragment) };
        }
        val btn_auth = btn_auth.setOnClickListener() { x ->
            if (
                authentication.signIn(et_email.text.toString(), et_password.text.toString())
                    .equals("success")
            )
                view?.let { Navigation.findNavController(it).navigate(R.id.profileFragment) };

        }
        val btn_google_sign_in = btn_google_sign_in.setOnClickListener() { x ->
            if (
                authentication.signInWithGoogle(getString(R.string.default_web_client_id))
                    .equals("success")
            )
                view?.let { Navigation.findNavController(it).navigate(R.id.profileFragment) };

        }
        getActivity()?.let {
            Snackbar.make(
                it.findViewById(android.R.id.content),
                "Failed",
                Snackbar.LENGTH_LONG
            ).show()
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_auth, container, false)
    }

    companion object {
        fun newInstance() : AuthFragment =
            AuthFragment()

    }
}
