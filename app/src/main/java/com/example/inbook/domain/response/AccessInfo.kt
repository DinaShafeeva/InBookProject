package com.example.inbook.domain.response


import com.google.gson.annotations.SerializedName

data class AccessInfo(
    @SerializedName("accessViewStatus")
    var accessViewStatus: String,
    @SerializedName("country")
    var country: String,
    @SerializedName("embeddable")
    var embeddable: Boolean,
    @SerializedName("epub")
    var epub: Epub,
    @SerializedName("pdf")
    var pdf: Pdf,
    @SerializedName("publicDomain")
    var publicDomain: Boolean,
    @SerializedName("quoteSharingAllowed")
    var quoteSharingAllowed: Boolean,
    @SerializedName("textToSpeechPermission")
    var textToSpeechPermission: String,
    @SerializedName("viewability")
    var viewability: String,
    @SerializedName("webReaderLink")
    var webReaderLink: String
)