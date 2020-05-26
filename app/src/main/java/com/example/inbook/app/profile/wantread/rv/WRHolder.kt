package com.example.inbook.app.profile.wantread.rv

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.inbook.R
import com.example.inbook.domain.mybooks.models.Book
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.book_profile_item.*

class WRHolder(
    override val containerView: View,
    private val clickLambda: (Book) -> Unit
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(book: Book) {

        tv_name_of_book_profile.text = book.nameOfBook
        tv_author_profile.text = book.author

        itemView.setOnClickListener {
            clickLambda(book)
        }
    }

    companion object {
        fun create(parent: ViewGroup, clickLambda: (Book) -> Unit) =
            WRHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.book_item, parent, false),
                clickLambda
            )
    }
}