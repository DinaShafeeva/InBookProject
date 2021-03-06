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
import com.example.inbook.app.mybooks.vm.QuoteViewModel
import com.example.inbook.app.profile.fragmentlists.rv.QuoteAdapter
import com.example.inbook.app.profile.vm.QuoteListViewModel
import com.example.inbook.app.profile.vm.WantToReadViewModel
import com.example.inbook.di.AppInjector
import com.example.inbook.domain.models.Quote
import kotlinx.android.synthetic.main.fragment_quotes_list.*
import javax.inject.Inject

class QuotesListFragment : Fragment() {
    private lateinit var bundle: Bundle
    private lateinit var viewModel: QuoteListViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getQuotes().observe(viewLifecycleOwner, Observer { list ->
            Log.d("ListQuotes", list.toString())
            if (rv_quotes.adapter == null){
                if (list?.size != 0) {
                    Log.d("ListInQuotes", list.toString())
                    val adapter = QuoteAdapter { quote ->
                        bundle = Bundle()
                        quote.name.let { bundle.putString("name", it) }
                        Navigation.findNavController(view).navigate(R.id.bookFragment, bundle)
                    }
                    rv_quotes.layoutManager = LinearLayoutManager(context)
                    rv_quotes.adapter = adapter
                }else tv_no_quotes.visibility = View.VISIBLE
            }
            (rv_quotes.adapter as? QuoteAdapter)?.submitList(list)
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_quotes_list, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AppInjector.plusQuoteListComponent().inject(this)
        initViewModel()
    }

    fun initViewModel(){
        viewModel = ViewModelProvider(this, viewModelFactory).get(QuoteListViewModel::class.java)
    }

    companion object {
        fun newInstance(): QuotesListFragment = QuotesListFragment()
    }
}
