package com.example.inbook.app.profile.fragmentlists.rv

import android.os.Bundle
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.inbook.domain.models.Book
import com.example.inbook.domain.models.Quote

class QuoteAdapter  (
    private val clickLambda: (Quote) -> Unit
) : ListAdapter<Quote, QuoteHolder>(
    Diff
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuoteHolder =
        QuoteHolder.create(
            parent,
            clickLambda
        )


    override fun onBindViewHolder(holder: QuoteHolder, position: Int) =
        holder.bind(getItem(position))
}
object Diff : DiffUtil.ItemCallback<Quote>() {

    override fun areItemsTheSame(oldItem: Quote, newItem: Quote): Boolean =
        oldItem.name == newItem.name

    override fun areContentsTheSame(oldItem: Quote, newItem: Quote): Boolean =
        oldItem == newItem

    override fun getChangePayload(oldItem: Quote, newItem: Quote): Any? {
        val diffBundle = Bundle()
        if (oldItem.name != newItem.name) {
            diffBundle.putString("name", newItem.name)
        }
        if (oldItem.text != newItem.text) {
            diffBundle.putString("text", newItem.text)
        }
        return if (diffBundle.isEmpty) null else diffBundle
    }
}