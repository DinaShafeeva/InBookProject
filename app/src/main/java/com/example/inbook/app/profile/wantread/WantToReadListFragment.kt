package com.example.inbook.app.profile.wantread

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.inbook.R
import com.example.inbook.app.profile.vm.ProfileViewModel
import com.example.inbook.app.profile.vm.WantToReadViewModel
import com.example.inbook.app.profile.wantread.rv.WRAdapter
import com.example.inbook.di.AppInjector
import com.example.inbook.domain.mybooks.models.Book
import kotlinx.android.synthetic.main.fragment_want_to_read_list.*
import javax.inject.Inject

class WantToReadListFragment : Fragment() {
    private var list: List<Book>? = null
    private lateinit var adapter: WRAdapter
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

        list = viewModel.getWantToReadBookList().value
        if (list != null) {
            if (list?.size != 0) {
                adapter = WRAdapter(list!!) { book ->
                    bundle = Bundle()
                    book.id.let { bundle.putString("id", it) }
                    Navigation.findNavController(view).navigate(R.id.bookFragment, bundle)
                }
                rv_want_to_read.layoutManager = LinearLayoutManager(context)
                rv_want_to_read.adapter = adapter
            } else tv_no_books_profile.visibility = View.VISIBLE
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AppInjector.plusWantToReadComponent().inject(this)
        initViewModel()
    }

    fun initViewModel(){
        viewModel = ViewModelProvider(this, viewModelFactory).get(WantToReadViewModel::class.java)
    }
}
