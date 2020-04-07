package com.example.inbook.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.inbook.R
import com.example.inbook.app.fragments.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), OnBookFragmentListener {

    private lateinit var fragmetContainer: FrameLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mOnNavigationItemSelectedListener =
            BottomNavigationView.OnNavigationItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.btn_book -> {
                        val firstFragment =
                            MyBooksFragment.newInstance()
                        openFragment(firstFragment)
                        true
                    }
                    R.id.btn_book_shelf -> {
                        val secondFragment =
                            SearchBookFragment.newInstance()
                        openFragment(secondFragment)
                        true
                    }
                    R.id.btn_profile -> {
                        val thirdFragment =
                            ProfileFragment.newInstance()
                        openFragment(thirdFragment)
                        true
                    }
                    else -> false
                }
            }
        btv_main.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    private fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction().apply { }
        transaction.replace(R.id.container_main, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun goToBookFragment(id: Int) {
        val fm: FragmentManager = getSupportFragmentManager();

        var fragment: Fragment? = fm.findFragmentById(R.id.container_main);
        if (fragment == null) {
            fragment = BookFragment();

            var bundle: Bundle = Bundle();
            bundle.putInt(BookFragment.KEY, id);
            fragment.setArguments(bundle);

            fm.beginTransaction()
                .add(R.id.container_main2, fragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit();
        }
    }
}
