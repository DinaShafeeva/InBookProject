package com.example.inbook.domain.response


import com.google.gson.annotations.SerializedName

data class PanelizationSummary(
    @SerializedName("containsEpubBubbles")
    var containsEpubBubbles: Boolean,
    @SerializedName("containsImageBubbles")
    var containsImageBubbles: Boolean
)