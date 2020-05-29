package com.example.inbook.app.mybooks.rv

import android.os.Bundle
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.inbook.domain.mybooks.models.Book

class BookAdapter(
    private val clickLambda: (Book) -> Unit
) : ListAdapter<Book, BookHolder>(
    Diff
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookHolder =
        BookHolder.create(
            parent,
            clickLambda
        )

   // override fun getItemCount(): Int = dataSource.size

    override fun onBindViewHolder(holder: BookHolder, position: Int) =
        holder.bind(getItem(position))
}

object Diff : DiffUtil.ItemCallback<Book>() {

    override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean =
        oldItem == newItem

    override fun getChangePayload(oldItem: Book, newItem: Book): Any? {
        val diffBundle = Bundle()
        if (oldItem.nameOfBook != newItem.nameOfBook) {
            diffBundle.putString("name", newItem.nameOfBook)
        }
        if (oldItem.author != newItem.author) {
            diffBundle.putString("author", newItem.author)
        }
        return if (diffBundle.isEmpty) null else diffBundle
    }
}