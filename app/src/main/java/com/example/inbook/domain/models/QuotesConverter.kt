package com.example.inbook.domain.models

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.TypeConverter
import java.sql.Array
import java.util.*
import java.util.stream.Collectors
import kotlin.collections.ArrayList

class QuotesConverter {
companion object {
    @RequiresApi(Build.VERSION_CODES.N)
    @TypeConverter
    @JvmStatic
    fun fromQuotes(quotes: ArrayList<String>): String {
        return quotes.stream().collect(Collectors.joining("///"))
    }

    @TypeConverter
    @JvmStatic
    fun toQuotes(data: String): ArrayList<String> {
        return data.trim().splitToSequence("///")
            .filter { it.isNotEmpty() }
            .toCollection(ArrayList<String>())
    }
}
}
