package com.example.inbook.domain.response


import com.google.gson.annotations.SerializedName

data class BookResponse(
    @SerializedName("items")
    var items: List<Item>,
    @SerializedName("kind")
    var kind: String,
    @SerializedName("totalItems")
    var totalItems: Int
)