package com.example.inbook.app.profile.fragmentlists.rv

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.inbook.app.mybooks.rv.Diff
import com.example.inbook.domain.models.Book

class WRAdapter (
    private val clickLambda: (Book) -> Unit
) : ListAdapter<Book, WRHolder>(
    Diff
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WRHolder =
        WRHolder.create(
            parent,
            clickLambda
        )

    override fun onBindViewHolder(holder: WRHolder, position: Int) =
        holder.bind(getItem(position))
}
