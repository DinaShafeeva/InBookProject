package com.example.inbook.app.mybooks

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.inbook.R
import com.example.inbook.app.mybooks.rv.BookAdapter
import com.example.inbook.app.mybooks.vm.BookViewModel
import com.example.inbook.data.mybooks.BookServiceImpl
import com.example.inbook.di.AppInjector
import com.example.inbook.domain.mybooks.models.Book
import kotlinx.android.synthetic.main.fragment_my_books.*
import javax.inject.Inject


class MyBooksFragment : Fragment() {
    private lateinit var  list: ArrayList<LiveData<Book>>
    private lateinit var adapter: BookAdapter
    private lateinit var bundle: Bundle

    private lateinit var viewModel: BookViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

  //  @Inject
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
            book.value?.id?.let { bundle.putString("id", it) }
            Navigation.findNavController(view).navigate(R.id.profileFragment, bundle)

        }
        rv_my_books.layoutManager = LinearLayoutManager(context)
        rv_my_books.adapter = adapter
        getData()

    }

    private fun getData() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AppInjector.plusBookComponent().inject(this)
        initViewModel()
    }

    fun initViewModel(){
        val viewModel by lazy {
            ViewModelProvider(
                this,
                viewModelFactory
            ).get(BookViewModel::class.java)
        }
        this.viewModel = viewModel
    }

    companion object {
        fun newInstance(): MyBooksFragment =
            MyBooksFragment()
    }
}
