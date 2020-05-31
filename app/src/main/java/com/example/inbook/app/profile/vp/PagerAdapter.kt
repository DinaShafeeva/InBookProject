package com.example.inbook.app.profile.vp

import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.inbook.app.profile.fragmentlists.LikedListFragment
import com.example.inbook.app.profile.fragmentlists.QuotesListFragment
import com.example.inbook.app.profile.fragmentlists.WantToReadListFragment

class PagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle)
    : FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int) = when (position) {
        0 -> WantToReadListFragment.newInstance()
        1 -> LikedListFragment.newInstance()
        else -> QuotesListFragment.newInstance()

    }
}