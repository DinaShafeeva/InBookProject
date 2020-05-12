package com.example.inbook.domain.mybooks.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Book (
    @PrimaryKey
    var id: String,
    var nameOfBook: String,
    var author: String,
    var status: Boolean,
    // true - was read, false - wasn't read
    var description: String
)