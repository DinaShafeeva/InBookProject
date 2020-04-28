package com.example.inbook.domain.response


import com.google.gson.annotations.SerializedName

data class IndustryIdentifier(
    @SerializedName("identifier")
    var identifier: String,
    @SerializedName("type")
    var type: String
)