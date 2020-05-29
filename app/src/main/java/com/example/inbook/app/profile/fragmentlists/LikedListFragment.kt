package com.example.inbook.app.profile.fragmentlists

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider

import com.example.inbook.R
import com.example.inbook.app.profile.vm.LikedViewModel
import com.example.inbook.app.profile.vm.WantToReadViewModel
import com.example.inbook.di.AppInjector
import javax.inject.Inject

class LikedListFragment : Fragment() {
    private lateinit var bundle: Bundle
    private lateinit var viewModel: LikedViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_liked_list, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AppInjector.plusLikedComponent().inject(this)
        initViewModel()
    }

    fun initViewModel(){
        viewModel = ViewModelProvider(this, viewModelFactory).get(LikedViewModel::class.java)
    }

}
