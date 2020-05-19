package com.example.inbook.domain.mybooks.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Book (
    @PrimaryKey
    var id: String,
    var nameOfBook: String,
    var author: String,
    var like: Boolean = false,
    // true - was liked, false - wasn't liked
    var description: String,
    var image: String,
    var status: Int = 0
    //0 - have read, 1 - want to read
)