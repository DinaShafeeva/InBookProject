package com.example.inbook.app.authentication

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.inbook.R
import com.example.inbook.app.authentication.vm.AuthViewModel
import com.example.inbook.data.authentication.AuthenticationImpl
import com.example.inbook.di.AppInjector
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_auth.*
import javax.inject.Inject

class AuthFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private var viewModel: AuthViewModel? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AppInjector.plusAuthComponent().inject(this)
        initViewModel()
    }

    fun initViewModel(){
         viewModel = ViewModelProvider(this, viewModelFactory).get(AuthViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_auth, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getActivity()?.findViewById<BottomNavigationView>(R.id.btv_main)?.visibility = View.GONE

        if (viewModel?.isAuth() ?: false) {
            Navigation.findNavController(view).navigate(R.id.profileFragment)
            Log.d("IsAuth", "true")
        } else Log.d("IsAuth", "false")

        btn_register.setOnClickListener { x ->
            if ((!et_email.text.toString().isEmpty()) && (!et_password.text.toString().isEmpty())) {
                if (
                    viewModel?.createAccount(et_email.text.toString(), et_password.text.toString(), view)
                        .equals("success")
                ) {
                    Navigation.findNavController(view).navigate(R.id.profileFragment)
                }
            }
        }
        btn_auth.setOnClickListener { x ->
            if ((!et_email.text.toString().isEmpty()) && (!et_password.text.toString().isEmpty())) {
                if (
                    viewModel?.signIn(et_email.text.toString(), et_password.text.toString(),view).equals("success")
                ) {
                    Navigation.findNavController(view).navigate(R.id.profileFragment)
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        AppInjector.clearAuthComponent()
    }

    companion object {
        fun newInstance() : AuthFragment =
            AuthFragment()
    }
}
