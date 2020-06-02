package com.example.inbook.domain.response


import com.google.gson.annotations.SerializedName

data class SaleInfo(
    @SerializedName("buyLink")
    var buyLink: String,
    @SerializedName("country")
    var country: String,
    @SerializedName("isEbook")
    var isEbook: Boolean,
    @SerializedName("listPrice")
    var listPrice: ListPrice,
    @SerializedName("offers")
    var offers: List<Offer>,
    @SerializedName("retailPrice")
    var retailPrice: RetailPriceX,
    @SerializedName("saleability")
    var saleability: String
)