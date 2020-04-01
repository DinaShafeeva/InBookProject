package com.example.inbook.app.recyclerView

import android.os.Bundle
import androidx.recyclerview.widget.DiffUtil
import com.example.inbook.domain.Book

object Diff : DiffUtil.ItemCallback<Book>() {

    override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean =
        oldItem.nameOfBook == newItem.nameOfBook

    override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean =
        oldItem.author == newItem.author

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