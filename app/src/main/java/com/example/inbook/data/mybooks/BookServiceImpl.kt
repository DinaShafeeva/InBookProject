package com.example.inbook.data.mybooks

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.inbook.domain.mybooks.models.Book
import com.example.inbook.domain.mybooks.services.BookService
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*


class BookServiceImpl: BookService {
    private var database: FirebaseDatabase = FirebaseDatabase.getInstance()
    private var myRef: DatabaseReference = database.reference
    private val mAuth = FirebaseAuth.getInstance()
    private var books: ArrayList<LiveData<Book>> = ArrayList()

    override fun getMyBooks(): ArrayList<LiveData<Book>> {
        mAuth.uid?.let {
            myRef.child(it).addValueEventListener(object : ValueEventListener {

                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    var book: Book
                    val name =
                        dataSnapshot.child("book").child("name").getValue(String::class.java)

//                    val t: GenericTypeIndicator<List<String>> =
//                        object : GenericTypeIndicator<List<String?>?>() {}
//                    val yourStringArray =
//                        dataSnapshot.getValue(t)
//                    var map: Map<String, MessageItem> = new LinkedHashMap<String, MessageItem>();
//                    if (dataSnapshot != null && dataSnapshot.getValue() != null) {
//
//                        for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
//                            HashMap<String,MessageItem> messageMap = (HashMap<String, MessageItem>) postSnapshot.getValue();
//                            Collection<MessageItem> messageItems = messageMap.values() ;
//                            List<MessageItem> messageItemList = new ArrayList<MessageItem>();
//                            messageItemList.addAll(messageItems);
//                        }}
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
                myRef.child(it).child("book").push().setValue(book)
//                myRef.child(it).child("book").push().child("id").setValue(book.value?.id)
//                myRef.child(it).child("book").push().child("name").setValue(book.value?.nameOfBook)
//                myRef.child(it).child("book").push().child("author").setValue(book.value?.author)
//                myRef.child(it).child("book").push().child("description").setValue(book.value?.description)
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
