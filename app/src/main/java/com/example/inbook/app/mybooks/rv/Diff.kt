package com.example.inbook.app.mybooks.rv

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.DiffUtil
import com.example.inbook.domain.mybooks.models.Book

object Diff : DiffUtil.ItemCallback<LiveData<Book>>() {

    override fun areItemsTheSame(oldItem: LiveData<Book>, newItem: LiveData<Book>): Boolean =
        oldItem.value?.nameOfBook == newItem.value?.nameOfBook

    override fun areContentsTheSame(oldItem: LiveData<Book>, newItem: LiveData<Book>): Boolean =
        oldItem.value?.author == newItem.value?.author

    override fun getChangePayload(oldItem: LiveData<Book>, newItem: LiveData<Book>): Any? {
        val diffBundle = Bundle()
        if (oldItem.value?.nameOfBook != newItem.value?.nameOfBook) {
            diffBundle.putString("name", newItem.value?.nameOfBook)
        }
        if (oldItem.value?.author != newItem.value?.author) {
            diffBundle.putString("author", newItem.value?.author)
        }
        return if (diffBundle.isEmpty) null else diffBundle
    }
}