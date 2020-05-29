package com.example.inbook.app.mybooks

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.inbook.R
import com.example.inbook.app.mybooks.vm.BookViewModel
import com.example.inbook.data.mybooks.BookServiceImpl
import com.example.inbook.di.AppInjector
import com.example.inbook.domain.mybooks.models.Book
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_book.*
import java.io.IOException
import javax.inject.Inject


class BookFragment : Fragment() {
    private lateinit var viewModel: BookViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



            // val book: LiveData<Book> =   viewModel.getBook(arguments?.getString("name") ?: "null")
        var id: String? = null
        viewModel.getBookFromDB(arguments?.getString("name") ?: "null").observe(viewLifecycleOwner, Observer { it ->
            try {
                tv_name_of_book_book_fragment.text = it.nameOfBook
                tv_author_book_fragment.text = it.author
                if (it.description.length<200) {
                    tv_description_book_fragment.text = it.description
                } else {
                    val newDesc = it.description.substring(0,200) + "..."
                    tv_description_book_fragment.text = newDesc
                }
                getImage(iv_image_book_fragment, it.image)
                id = it.id

                if (viewModel.isBookWasRead(id)){
                    btn_comment.visibility = View.VISIBLE
                    btn_like.visibility = View.VISIBLE
                }

                btn_have_read.setOnClickListener{ view ->
                    btn_comment.visibility = View.VISIBLE
                    btn_like.visibility = View.VISIBLE
                    viewModel.addBook(MutableLiveData<Book>(it))
                }
                btn_like.setOnClickListener{view ->
                    viewModel.like(MutableLiveData<Book>(it))
                }

//                if (viewModel.isBookWasRead(book)){
//                    btn_comment.visibility = View.VISIBLE
//                    btn_like.visibility = View.VISIBLE
//                }
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


//        btn_have_read.setOnClickListener{
//            btn_comment.visibility = View.VISIBLE
//            btn_like.visibility = View.VISIBLE
//            viewModel.addBook(book)
//        }
//
//        btn_like.setOnClickListener{
//            viewModel.like(book)
//        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AppInjector.plusBookComponent().inject(this)
        initViewModel()
    }

    fun initViewModel(){
        viewModel = ViewModelProvider(this, viewModelFactory).get(BookViewModel::class.java)
    }

    fun getImage(image: ImageView, source: String?) {
        val newSource = "https" + source?.substring(4)
        Glide
            .with(image.context)
            .load(newSource)
            .into(image)
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
