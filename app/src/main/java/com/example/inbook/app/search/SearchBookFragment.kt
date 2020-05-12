package com.example.inbook.app.search

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.inbook.R
import com.example.inbook.app.mybooks.vm.BookViewModel
import com.example.inbook.di.AppInjector
import com.example.inbook.domain.mybooks.models.Book
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_search_book.*
import java.io.IOException
import javax.inject.Inject


class SearchBookFragment : Fragment() {
    private var viewModel: BookViewModel? = null
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//        AppInjector.plusBookComponent().inject(this)
//        initViewModel()
//    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        AppInjector.plusBookComponent().inject(this)
        initViewModel()
    }

    fun initViewModel(){
        viewModel = ViewModelProvider(this, viewModelFactory).get(BookViewModel::class.java)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
      //  super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.tb_search, menu);
        //activity?.getMenuInflater()?.inflate(R.menu.tb_search, menu)
        val searchItem: MenuItem = menu.findItem(R.id.search)
        val searchView: SearchView = searchItem.actionView as SearchView
        Log.e("MENU", "open")

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                queryTextSubmit(query)
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })
        super.onCreateOptionsMenu(menu, inflater)
    }

    fun queryTextSubmit(query: String): Boolean {
         viewModel?.getBook(query)?.observe(this, Observer {
            try {
                tv_name_search.text = it.nameOfBook
                tv_author_search.text = it.author
                tv_description_search.text = it.description

            } catch (e: IOException) {
                getActivity()?.let {
                    Snackbar.make(
                        it.findViewById(android.R.id.content),
                        "Can't find the book",
                        Snackbar.LENGTH_LONG
                    ).show()
                }
            }
        })

        return false;
    }



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_search_book, container, false)

    }

//    override fun onCreate(savedInstanceState: Bundle?) {
//        setHasOptionsMenu(true)
//        super.onCreate(savedInstanceState)
//    }

    override fun onDestroy() {
        super.onDestroy()
        AppInjector.clearBookComponent()
    }
    companion object {
        fun newInstance(): SearchBookFragment =
            SearchBookFragment()
    }
}
