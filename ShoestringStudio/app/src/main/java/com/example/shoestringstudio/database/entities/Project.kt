package com.example.shoestringstudio.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "Project")
data class Project (
    @PrimaryKey(autoGenerate = true)  val projectId: Long?,
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "lastEdit") val lastEdit: Date?,
    @ColumnInfo(name = "trackAmount") val trackAmount: Int?,
    @ColumnInfo(name = "userId") val userId: Long?
)