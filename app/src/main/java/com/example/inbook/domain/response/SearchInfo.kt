package com.example.inbook.domain.response


import com.google.gson.annotations.SerializedName

data class SearchInfo(
    @SerializedName("textSnippet")
    var textSnippet: String
)