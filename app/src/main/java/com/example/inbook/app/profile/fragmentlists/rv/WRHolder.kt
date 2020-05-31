package com.example.inbook.app.profile.fragmentlists.rv

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.inbook.R
import com.example.inbook.domain.models.Book
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.book_profile_item.*

class WRHolder(
    override val containerView: View,
    private val clickLambda: (Book) -> Unit
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(book: Book) {
        Log.d("BookItem", book.toString())
        tv_name_of_book_profile.text = book.nameOfBook
        tv_author_profile.text = book.author
        itemView.setOnClickListener {
            clickLambda(book)
        }
    }

    companion object {
        fun create(parent: ViewGroup, clickLambda: (Book) -> Unit) =
            WRHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.book_profile_item, parent, false),
                clickLambda
            )
    }
}
