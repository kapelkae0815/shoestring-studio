package com.example.shoestringstudio.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Track")
data class Track (
    @PrimaryKey(autoGenerate = true) val trackId: Long?,
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "volume") val volume: Int?,
    @ColumnInfo(name = "length") val length: Int?,
    @ColumnInfo(name = "startOffset") val startOffset: Int?,
    @ColumnInfo(name = "projectId") val projectId: Long?,
    @ColumnInfo(name = "pathName") val pathName: String
)

