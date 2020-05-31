package com.example.inbook.app.profile.fragmentlists.rv

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.inbook.R
import com.example.inbook.domain.models.Book
import com.example.inbook.domain.models.Quote
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.quote_item.*

class QuoteHolder (
    override val containerView: View,
    private val clickLambda: (Quote) -> Unit
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(quote: Quote) {

        Log.d("Quote", quote.toString())
        tv_name_of_book_quote.text = quote.name
        tv_quote.text = quote.text

        itemView.setOnClickListener {
            clickLambda(quote)
        }
    }

    companion object {
        fun create(parent: ViewGroup, clickLambda: (Quote) -> Unit) =
            QuoteHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.quote_item, parent, false),
                clickLambda
            )
    }
}