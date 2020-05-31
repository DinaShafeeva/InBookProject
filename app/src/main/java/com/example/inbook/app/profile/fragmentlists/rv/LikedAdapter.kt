package com.example.inbook.app.profile.fragmentlists.rv

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.inbook.app.mybooks.rv.Diff
import com.example.inbook.domain.models.Book

class LikedAdapter  (
    private val clickLambda: (Book) -> Unit
) : ListAdapter<Book, LikedHolder>(
    Diff
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LikedHolder =
        LikedHolder.create(
            parent,
            clickLambda
        )

    //   override fun getItemCount(): Int = dataSource.size

    override fun onBindViewHolder(holder: LikedHolder, position: Int) =
        holder.bind(getItem(position))
}