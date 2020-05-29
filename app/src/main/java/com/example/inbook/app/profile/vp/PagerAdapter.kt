package com.example.inbook.app.profile.vp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.inbook.R
import com.example.inbook.app.profile.fragmentlists.LikedListFragment
import com.example.inbook.app.profile.fragmentlists.WantToReadListFragment

class PagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle)
    : FragmentStateAdapter(fragmentManager, lifecycle) {

//    override fun isViewFromObject(view: View, `object`: Any): Boolean = view == `object`
//
//    override fun getCount(): Int = 2
//
//    override fun instantiateItem(container: ViewGroup, position: Int): Any {
//        val layout1 = LayoutInflater.from(context).inflate(R.layout.fragment_want_to_read_list, container, false)
//        val layout2 = LayoutInflater.from(context).inflate(R.layout.fragment_liked_list, container, false)
//
//        if (position == 0) {
//            container.addView(layout1)
//            return layout1
//        } else {
//            container.addView(layout2)
//            return layout2
//        }
//    }
//
//    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
//        container.removeView(`object` as LinearLayout)
//    }

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int) = when (position) {
        0 -> WantToReadListFragment.newInstance()
        else -> LikedListFragment.newInstance()

    }
}