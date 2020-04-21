package com.example.inbook.app.mybooks

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.inbook.R
import com.example.inbook.app.mybooks.rv.BookAdapter
import com.example.inbook.app.mybooks.vm.BookViewModel
import com.example.inbook.data.mybooks.BookServiceImpl
import com.example.inbook.domain.mybooks.models.Book
import kotlinx.android.synthetic.main.fragment_my_books.*
import javax.inject.Inject


class MyBooksFragment : Fragment() {
    private lateinit var  list: ArrayList<LiveData<Book>>
    private lateinit var adapter: BookAdapter
    private lateinit var viewModel: BookViewModel
    private lateinit var bundle: Bundle

    @Inject
    lateinit var bookService: BookServiceImpl

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_my_books, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        list = bookService.getMyBooks()
        adapter = BookAdapter(list) { book ->
            bundle = Bundle()
            book.value?.id?.let { bundle.putInt("id", it) }
            Navigation.findNavController(view).navigate(R.id.profileFragment, bundle)

        }
        rv_my_books.layoutManager = LinearLayoutManager(context)
        rv_my_books.adapter = adapter
        viewModel = BookViewModel()
        getData()

    }

    private fun getData() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    companion object {
        fun newInstance(): MyBooksFragment =
            MyBooksFragment()
    }
}
