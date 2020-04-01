package com.example.inbook.domain

data class Book (
    var id: Int,
    var nameOfBook: String,
    var author: String,
    var status: Boolean,
    // true - was read, false - wasn't read
    var description: String
)