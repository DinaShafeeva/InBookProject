package com.example.inbook.app.profile.fragmentlists

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.inbook.R
import com.example.inbook.app.profile.vm.WantToReadViewModel
import com.example.inbook.app.profile.fragmentlists.rv.WRAdapter
import com.example.inbook.di.AppInjector
import com.example.inbook.domain.models.Book
import kotlinx.android.synthetic.main.fragment_want_to_read_list.*
import javax.inject.Inject

class WantToReadListFragment : Fragment() {
    private var list: List<Book>? = null
    private lateinit var bundle: Bundle
    private lateinit var viewModel: WantToReadViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_want_to_read_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("FragmentWant", "onView Created")

        viewModel.getWantToReadBookList().observe(viewLifecycleOwner, Observer { list ->
            Log.d("ListWant", list.toString())
            if (rv_want_to_read.adapter == null){
                if (list?.size != 0) {
                    Log.d("ListInWantToRead", list.toString())
                    val adapter = WRAdapter { book ->
                        bundle = Bundle()
                        book.nameOfBook.let { bundle.putString("name", it) }
                        Navigation.findNavController(view).navigate(R.id.bookFragment, bundle)
                    }
                    rv_want_to_read.layoutManager = LinearLayoutManager(context)
                    rv_want_to_read.adapter = adapter
                }else tv_no_books_profile_want.visibility = View.VISIBLE
            }
            (rv_want_to_read.adapter as? WRAdapter)?.submitList(list)
        })
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AppInjector.plusWantToReadComponent().inject(this)
        initViewModel()
    }

    fun initViewModel(){
        viewModel = ViewModelProvider(this, viewModelFactory).get(WantToReadViewModel::class.java)
    }

    companion object {
        fun newInstance(): WantToReadListFragment = WantToReadListFragment()
    }
}
