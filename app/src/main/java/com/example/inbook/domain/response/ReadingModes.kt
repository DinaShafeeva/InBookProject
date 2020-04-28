package com.example.inbook.domain.response


import com.google.gson.annotations.SerializedName

data class ReadingModes(
    @SerializedName("image")
    var image: Boolean,
    @SerializedName("text")
    var text: Boolean
)