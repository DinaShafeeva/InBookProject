package com.example.inbook.app.recyclerView

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.inbook.domain.Book

class BookAdapter(
    private var dataSource: ArrayList<Book>,
    private val clickLambda: (Book) -> Unit
) : ListAdapter<Book, BookHolder>(
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

    fun updateList(newList: ArrayList<Book>) {
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