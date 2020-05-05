package com.example.inbook.domain.mybooks.models

data class Book (
    var id: String,
    var nameOfBook: String,
    var author: String,
    var status: Boolean,
    // true - was read, false - wasn't read
    var description: String
)