package com.example.inbook.app.profile

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.example.inbook.R
import com.example.inbook.app.profile.vm.ProfileViewModel
import com.example.inbook.app.profile.vp.PagerAdapter
import com.example.inbook.di.AppInjector
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_profile.*
import javax.inject.Inject


class ProfileFragment : Fragment() {
    private lateinit var viewModel: ProfileViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       // tv_name_profile.text = viewModel.getName().value
        getActivity()?.findViewById<BottomNavigationView>(R.id.btv_main)?.visibility = View.VISIBLE
//        viewModel.getName().observe(viewLifecycleOwner, Observer {
            tv_name_profile.text = viewModel.getName().value
//        })
//
        viewModel.getBooksCount().observe(viewLifecycleOwner, Observer {
            val booksCount = "$it books was read"
            tv_books_count.text = booksCount
        })
//        val booksCount = viewModel.getBooksCount().value + " books was read"
 //       tv_books_count.text = booksCount

        val viewPager = vp_profile as ViewPager
        viewPager.adapter = PagerAdapter(context)

        tabs_profile.setupWithViewPager(vp_profile)
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

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AppInjector.plusProfileComponent().inject(this)
        initViewModel()
    }

    fun initViewModel(){
        viewModel = ViewModelProvider(this, viewModelFactory).get(ProfileViewModel::class.java)
    }

    companion object {
        fun newInstance(): ProfileFragment =
            ProfileFragment()
    }
}
