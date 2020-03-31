package com.example.inbook.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.inbook.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

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
}
