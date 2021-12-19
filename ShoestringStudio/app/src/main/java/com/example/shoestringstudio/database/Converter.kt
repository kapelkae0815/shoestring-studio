package com.example.shoestringstudio.database

import androidx.room.TypeConverter
import java.util.*

class Converter {
    @TypeConverter
    fun dateToLong(date: Date?): Long? {
        return date?.time
    }
    @TypeConverter
    fun longToDate(timestamp: Long?): Date? {
        return timestamp?.let { Date(it) }
    }
}