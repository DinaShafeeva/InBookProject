package com.example.inbook.app.mybooks.rv

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.inbook.R
import com.example.inbook.domain.mybooks.models.Book
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.book_item.*

class BookHolder(
    override val containerView: View,
    private val clickLambda: (LiveData<Book>) -> Unit
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(book: LiveData<Book>) {

        tv_name_of_book.text = book.value?.nameOfBook
        tv_author.text = book.value?.author
        tv_description.text = book.value?.description

        itemView.setOnClickListener {
            clickLambda(book)
        }
    }

    companion object {
        fun create(parent: ViewGroup, clickLambda: (LiveData<Book>) -> Unit) =
            BookHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.book_item, parent, false),
                clickLambda
            )
    }
}