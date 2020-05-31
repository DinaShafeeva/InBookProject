package com.example.inbook.app.profile

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.viewpager.widget.ViewPager
import com.example.inbook.R
import com.example.inbook.app.profile.vm.ProfileViewModel
import com.example.inbook.app.profile.vp.PagerAdapter
import com.example.inbook.di.AppInjector
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_profile.*
import javax.inject.Inject


class ProfileFragment : Fragment() {
    private lateinit var viewModel: ProfileViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getActivity()?.findViewById<BottomNavigationView>(R.id.btv_main)?.visibility = View.VISIBLE

        viewModel.getBooksCount().observe(viewLifecycleOwner, Observer {
            val booksCount = "Read books: $it"
            tv_books_count.text = booksCount
        })

        viewModel.getLikedBooksCount().observe(viewLifecycleOwner, Observer {
            val booksCount = "Liked books: $it"
            tv_books_liked_count.text = booksCount
        })

        vp_profile.adapter = PagerAdapter(childFragmentManager, lifecycle)

        TabLayoutMediator(tabs_profile, vp_profile,
            TabLayoutMediator.TabConfigurationStrategy { tabs, position ->
                when (position) {
                    0 -> {
                        tabs.text = "Want to read"
                    }
                    1 -> {
                        tabs.text = "Liked books"
                    }
                    2 -> {
                        tabs.text = "Quotes"
                    }
                }
            }).attach()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        tb_profile?.navigationIcon =
            view?.context?.let { ContextCompat.getDrawable(it, R.drawable.icn_out) }
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onOptionsItemSelected(item: MenuItem):Boolean {
        Log.w("SignOut", "success");
        viewModel.signOut()
        view?.let { Navigation.findNavController(it).navigate(R.id.authFragment) }
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
