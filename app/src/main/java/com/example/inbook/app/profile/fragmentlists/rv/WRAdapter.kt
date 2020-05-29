package com.example.inbook.app.profile.fragmentlists.rv

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.inbook.app.mybooks.rv.Diff
import com.example.inbook.app.mybooks.rv.DiffUtil
import com.example.inbook.domain.mybooks.models.Book

class WRAdapter (
    private var dataSource: List<Book>,
    private val clickLambda: (Book) -> Unit
) : ListAdapter<Book, WRHolder>(
    Diff
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WRHolder =
        WRHolder.create(
            parent,
            clickLambda
        )

    override fun getItemCount(): Int = dataSource.size

    override fun onBindViewHolder(holder: WRHolder, position: Int) =
        holder.bind(dataSource[position])

    fun updateList(newList: List<Book>) {
        androidx.recyclerview.widget.DiffUtil.calculateDiff(
            DiffUtil(
                this.dataSource,
                newList
            ),
            true
        )
            .dispatchUpdatesTo(this)
        // this.dataSource.clear()
        //this.dataSource.addAll(newList)
    }
}