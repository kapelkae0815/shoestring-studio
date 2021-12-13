package com.example.shoestringstudio.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Blob

@Entity(tableName = "SoundSource")
data class SoundSource (
    @PrimaryKey(autoGenerate = true)  val soundId: Long?,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "length") val length: Int,
    //@ColumnInfo(name = "data") val data: Blob,
    @ColumnInfo(name = "fileType") val fileType: String,
    @ColumnInfo(name = "pathName") val pathName: String

)