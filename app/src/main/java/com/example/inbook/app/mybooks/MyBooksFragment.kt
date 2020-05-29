package com.example.inbook.app.mybooks

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.inbook.R
import com.example.inbook.app.mybooks.rv.BookAdapter
import com.example.inbook.app.mybooks.vm.BookViewModel
import com.example.inbook.app.mybooks.vm.ListBooksViewModel
import com.example.inbook.di.AppInjector
import com.example.inbook.domain.mybooks.models.Book
import kotlinx.android.synthetic.main.fragment_my_books.*
import javax.inject.Inject


class MyBooksFragment : Fragment() {
    private lateinit var bundle: Bundle

    private lateinit var viewModel: ListBooksViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_my_books, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getBooks().observe(viewLifecycleOwner, Observer { list ->
         if (rv_my_books.adapter == null){
            if (list?.size != 0) {
                Log.d("ListInFragment", list.toString())
                val adapter = BookAdapter { book ->
                    bundle = Bundle()
                    book.nameOfBook.let { bundle.putString("name", it) }
                    Navigation.findNavController(view).navigate(R.id.bookFragment, bundle)
                }
                rv_my_books.layoutManager = LinearLayoutManager(context)
                rv_my_books.adapter = adapter
            }else tv_no_books.visibility = View.VISIBLE
            }
            (rv_my_books.adapter as? BookAdapter)?.submitList(list)
        })

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AppInjector.plusListBooksComponent().inject(this)
        initViewModel()
    }

    fun initViewModel() {
        viewModel = ViewModelProvider(this, viewModelFactory).get(ListBooksViewModel::class.java)
    }

    companion object {
        fun newInstance(): MyBooksFragment =
            MyBooksFragment()
    }
}
