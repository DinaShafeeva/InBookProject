package com.example.inbook.app.mybooks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.inbook.R
import com.example.inbook.app.mybooks.vm.BookViewModel
import com.example.inbook.data.mybooks.di.ViewModelFactory
import com.example.inbook.data.mybooks.BookServiceImpl
import kotlinx.android.synthetic.main.fragment_book.*
import javax.inject.Inject


class BookFragment : Fragment() {
    private lateinit var viewModel: BookViewModel
    private lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var bookService: BookServiceImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this,
            viewModelFactory).get(BookViewModel::class.java)
        val book = viewModel.getBook(arguments?.getInt("id") ?: 0)

        tv_name_of_book_book_fragment.text = book.value?.nameOfBook
        tv_author_book_fragment.text = book.value?.author
        tv_description_book_fragment.text = book.value?.description

        if (bookService.isBookWasRead(book)){
            btn_comment.visibility = View.VISIBLE
            btn_like.visibility = View.VISIBLE
        }

        btn_have_read.setOnClickListener{
            btn_comment.visibility = View.VISIBLE
            btn_like.visibility = View.VISIBLE
            bookService.addBook(book)
        }

        btn_like.setOnClickListener{
            bookService.like(book)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_book, container, false)
    }

    companion object {
        const val KEY = "text"
        fun newInstance(): BookFragment =
            BookFragment()
    }
}
