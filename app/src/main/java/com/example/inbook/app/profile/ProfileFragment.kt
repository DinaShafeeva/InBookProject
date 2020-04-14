package com.example.inbook.app.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.inbook.R


class ProfileFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    fun onCreateOptionsMenu(menu: Menu?): Boolean {
        activity?.getMenuInflater()?.inflate(R.menu.tb_profile, menu)
        return true
    }

    companion object {
        fun newInstance(): ProfileFragment =
            ProfileFragment()
    }
}
