package com.example.inbook.app.mybooks.rv

import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.ListAdapter
import com.example.inbook.domain.mybooks.models.Book

class BookAdapter(
    private var dataSource: ArrayList<LiveData<Book>>,
    private val clickLambda: (LiveData<Book>) -> Unit
) : ListAdapter<LiveData<Book>, BookHolder>(
    Diff
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookHolder =
        BookHolder.create(
            parent,
            clickLambda
        )

    override fun getItemCount(): Int = dataSource.size

    override fun onBindViewHolder(holder: BookHolder, position: Int) =
        holder.bind(dataSource[position])

    fun updateList(newList: ArrayList<LiveData<Book>>) {
        androidx.recyclerview.widget.DiffUtil.calculateDiff(
            DiffUtil(
                this.dataSource,
                newList
            ),
            true
        )
            .dispatchUpdatesTo(this)
        this.dataSource.clear()
        this.dataSource.addAll(newList)
    }
}