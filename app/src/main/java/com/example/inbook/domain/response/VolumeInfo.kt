package com.example.inbook.domain.response


import com.google.gson.annotations.SerializedName

data class VolumeInfo(
    @SerializedName("allowAnonLogging")
    var allowAnonLogging: Boolean,
    @SerializedName("authors")
    var authors: List<String>,
    @SerializedName("averageRating")
    var averageRating: Float,
    @SerializedName("canonicalVolumeLink")
    var canonicalVolumeLink: String,
    @SerializedName("categories")
    var categories: List<String>,
    @SerializedName("contentVersion")
    var contentVersion: String,
    @SerializedName("description")
    var description: String,
    @SerializedName("imageLinks")
    var imageLinks: ImageLinks,
    @SerializedName("industryIdentifiers")
    var industryIdentifiers: List<IndustryIdentifier>,
    @SerializedName("infoLink")
    var infoLink: String,
    @SerializedName("language")
    var language: String,
    @SerializedName("maturityRating")
    var maturityRating: String,
    @SerializedName("pageCount")
    var pageCount: Int,
    @SerializedName("panelizationSummary")
    var panelizationSummary: PanelizationSummary,
    @SerializedName("previewLink")
    var previewLink: String,
    @SerializedName("printType")
    var printType: String,
    @SerializedName("publishedDate")
    var publishedDate: String,
    @SerializedName("publisher")
    var publisher: String,
    @SerializedName("ratingsCount")
    var ratingsCount: Int,
    @SerializedName("readingModes")
    var readingModes: ReadingModes,
    @SerializedName("subtitle")
    var subtitle: String,
    @SerializedName("title")
    var title: String
)