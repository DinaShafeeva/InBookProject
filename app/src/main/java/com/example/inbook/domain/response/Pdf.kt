package com.example.inbook.domain.response


import com.google.gson.annotations.SerializedName

data class Pdf(
    @SerializedName("acsTokenLink")
    var acsTokenLink: String,
    @SerializedName("downloadLink")
    var downloadLink: String,
    @SerializedName("isAvailable")
    var isAvailable: Boolean
)