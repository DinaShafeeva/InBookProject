package com.example.inbook.data.mybooks

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.inbook.domain.mybooks.models.Book
import com.example.inbook.domain.mybooks.services.BookService
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*


class BookServiceImpl: BookService {
    private var database: FirebaseDatabase = FirebaseDatabase.getInstance()
    var myRef: DatabaseReference = database.reference
    val mAuth = FirebaseAuth.getInstance()

    var books: ArrayList<LiveData<Book>> = ArrayList<LiveData<Book>>()

    override fun getMyBooks(): ArrayList<LiveData<Book>> {
        mAuth.uid?.let {
            myRef.child(it).addValueEventListener(object : ValueEventListener {

                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    var book: Book
                    val name =
                        dataSnapshot.child("book").child("name").getValue(String::class.java)
                }

                override fun onCancelled(error: DatabaseError) { // Failed to read value
                    Log.w("///", "Failed to read value.", error.toException())
                }
            })
        }
        return books
    }

        override fun addBook(book: LiveData<Book>) {
            mAuth.uid?.let {
                myRef.child(it).child("book").push().child("id").setValue(book.value?.id)
                myRef.child(it).child("book").push().child("name").setValue(book.value?.nameOfBook)
                myRef.child(it).child("book").push().child("author").setValue(book.value?.author)
                myRef.child(it).child("book").push().child("description")
                    .setValue(book.value?.description)
            }
        }

        override fun isBookWasRead(book: LiveData<Book>): Boolean {
            mAuth.uid?.let {
                return myRef.child(it).child("book").child(book.value?.nameOfBook.toString()).key != null
            }
            return false
        }

        override fun like(book: LiveData<Book>) {
            mAuth.uid?.let {
                myRef.child(it).child("book").push().child("like").setValue(true)
            }
        }

    }
