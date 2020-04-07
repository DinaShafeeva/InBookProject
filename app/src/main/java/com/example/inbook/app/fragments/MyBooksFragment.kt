package com.example.inbook.app.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.inbook.R
import com.example.inbook.app.recyclerView.BookAdapter
import com.example.inbook.app.viewModels.BookViewModel
import com.example.inbook.domain.Book
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_my_books.*


class MyBooksFragment : Fragment() {
    private lateinit var  list: ArrayList<Book>
    private lateinit var adapter: BookAdapter
    private lateinit var viewModel: BookViewModel
//    private lateinit var mAuth: FirebaseAuth
//    private lateinit var database: FirebaseDatabase

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        arguments?.let {
//            param1 = it.getString(ARG_PARAM1)
//            param2 = it.getString(ARG_PARAM2)
//        }
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_my_books, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        list = ArrayList<Book>()
        adapter = BookAdapter(list) { book ->
            navigateTo(book.id)
        }
        rv_my_books.layoutManager = LinearLayoutManager(this)
        rv_my_books.adapter = adapter
        viewModel = BookViewModel()
        getData()

//        database = FirebaseDatabase.getInstance()
//        var myRef: DatabaseReference = database.reference
//        var user: FirebaseUser? = mAuth.currentUser

    }

    private fun getData() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    companion object {
        fun newInstance(): MyBooksFragment =
            MyBooksFragment()
    }
}
