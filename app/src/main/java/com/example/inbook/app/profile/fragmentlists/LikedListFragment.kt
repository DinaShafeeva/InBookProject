package com.example.inbook.app.profile.fragmentlists

import android.content.Context
import android.os.Bundle
import android.provider.ContactsContract
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
import com.example.inbook.app.profile.fragmentlists.rv.LikedAdapter
import com.example.inbook.app.profile.vm.LikedViewModel
import com.example.inbook.app.profile.vm.ProfileViewModel
import com.example.inbook.app.profile.vm.WantToReadViewModel
import com.example.inbook.di.AppInjector
import kotlinx.android.synthetic.main.fragment_liked_list.*
import kotlinx.android.synthetic.main.fragment_my_books.*
import kotlinx.android.synthetic.main.fragment_want_to_read_list.*
import javax.inject.Inject

class LikedListFragment : Fragment() {
    private lateinit var bundle: Bundle
    private lateinit var viewModel: LikedViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("FragmentLiked", "onView Created")

        viewModel.getLikedList().observe(viewLifecycleOwner, Observer { list ->
            Log.d("ListLiked", list.toString())
            if (rv_liked.adapter == null){
                if (list?.size != 0) {
                    Log.d("ListInLiked", list.toString())
                    val adapter = LikedAdapter { book ->
                        bundle = Bundle()
                        book.nameOfBook.let { bundle.putString("name", it) }
                        Navigation.findNavController(view).navigate(R.id.bookFragment, bundle)
                    }
                    rv_liked.layoutManager = LinearLayoutManager(context)
                    rv_liked.adapter = adapter
                }else tv_no_books_liked.visibility = View.VISIBLE
            }
            (rv_liked.adapter as? LikedAdapter)?.submitList(list)
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_liked_list, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AppInjector.plusLikedComponent().inject(this)
        initViewModel()
    }

    fun initViewModel(){
        viewModel = ViewModelProvider(this, viewModelFactory).get(LikedViewModel::class.java)
    }

    companion object {
        fun newInstance(): LikedListFragment = LikedListFragment()
    }

}
