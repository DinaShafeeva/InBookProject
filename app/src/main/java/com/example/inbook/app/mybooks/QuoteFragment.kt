package com.example.inbook.app.mybooks

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide

import com.example.inbook.R
import com.example.inbook.app.mybooks.vm.QuoteViewModel
import com.example.inbook.di.AppInjector
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_quote.*
import java.io.IOException
import javax.inject.Inject

class QuoteFragment : Fragment() {
    private lateinit var viewModel: QuoteViewModel
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getBook(arguments?.getString("name") ?: "null").observe(viewLifecycleOwner, Observer { it ->
            try {
                tv_name_of_book_quote_fragment.text = it.nameOfBook
                tv_author_quote_fragment.text = it.author
                getImage(iv_image_quote_fragment, it.image)
            }catch (e: IOException) {
                getActivity()?.let {
                    Snackbar.make(
                        it.findViewById(android.R.id.content),
                        "Can't find the book",
                        Snackbar.LENGTH_LONG
                    ).show()
                }
            }
        })

        btn_write_quote_quote_fragment.setOnClickListener{
            if (et_quote_quote_fragment.text != null){
                viewModel.addQuote(et_quote_quote_fragment.text.toString(), arguments?.getString("name") ?: "null")
                Snackbar.make(
                    view,
                    "New quote was created",
                    Snackbar.LENGTH_LONG
                ).show()
            }
        }
    }

    fun getImage(image: ImageView, source: String?) {
        val newSource = "https" + source?.substring(4)
        Glide
            .with(image.context)
            .load(newSource)
            .into(image)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AppInjector.plusQuoteComponent().inject(this)
        initViewModel()
    }

    fun initViewModel(){
        viewModel = ViewModelProvider(this, viewModelFactory).get(QuoteViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_quote, container, false)
    }

    companion object {
        fun newInstance(): QuoteFragment = QuoteFragment()
    }
}
