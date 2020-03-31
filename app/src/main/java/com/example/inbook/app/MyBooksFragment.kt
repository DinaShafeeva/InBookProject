package com.example.inbook.app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.inbook.R
import com.example.inbook.data.Book
import com.example.inbook.domain.BookAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class MyBooksFragment : Fragment() {
    private lateinit var  list: ArrayList<Book>
    private lateinit var adapter: BookAdapter
    private lateinit var mAuth: FirebaseAuth
    private lateinit var database: FirebaseDatabase

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

        database = FirebaseDatabase.getInstance()
        var myRef: DatabaseReference = database.reference
        var user: FirebaseUser? = mAuth.currentUser


//        myRef.addValueEventListener(object : ValueEventListener {
//            override fun onDataChange(dataSnapshot: DataSnapshot) {
//
//                val value =
//                    dataSnapshot.getValue(String::class.java)!!
//                Log.d(TAG, "Value is: $value")
//            }
//
//            override fun onCancelled(error: DatabaseError) { // Failed to read value
//                Log.w(TAG, "Failed to read value.", error.toException())
//            }
//        })
    }

    companion object {
        fun newInstance(): MyBooksFragment = MyBooksFragment()
    }
}
