package com.example.inbook.app.mybooks.rv

import androidx.recyclerview.widget.DiffUtil
import com.example.inbook.domain.mybooks.Book

class DiffUtil (private val oldList: List<Book>, private val newList: List<Book>) :
    DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition] == newList[newItemPosition]

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition] == newList[newItemPosition]

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size
}