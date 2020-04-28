package com.example.inbook.domain.response


import com.google.gson.annotations.SerializedName

data class RetailPrice(
    @SerializedName("amountInMicros")
    var amountInMicros: Int,
    @SerializedName("currencyCode")
    var currencyCode: String
)