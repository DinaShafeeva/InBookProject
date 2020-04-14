package com.example.inbook.app.mybooks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.inbook.R


class BookFragment : Fragment() {
    //когда нажимается кнопка читал - визибил у лайк и коммента - тру

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val id:Int = arguments?.getInt("id") ?: 0

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_book, container, false)
    }

    companion object {
        const val KEY = "text"
        fun newInstance(): BookFragment =
            BookFragment()
    }
}
