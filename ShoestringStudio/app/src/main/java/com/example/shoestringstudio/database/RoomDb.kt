package com.example.shoestringstudio.database

import android.content.Context
import androidx.room.*
import com.example.shoestringstudio.database.daos.ProjectDao
import com.example.shoestringstudio.database.daos.SoundSourceDao
import com.example.shoestringstudio.database.daos.TrackDao
import com.example.shoestringstudio.database.daos.UserDao
import com.example.shoestringstudio.database.entities.Project
import com.example.shoestringstudio.database.entities.SoundSource
import com.example.shoestringstudio.database.entities.Track
import com.example.shoestringstudio.database.entities.User

@Database(entities = [Project::class, SoundSource::class,
    Track::class, User::class], version = 1)
abstract class RoomDb: RoomDatabase() {
    abstract fun projectDao(): ProjectDao
    abstract fun soundSourceDao(): SoundSourceDao
    abstract fun trackDao(): TrackDao
    abstract fun userDao(): UserDao


    companion object {
        private var instance: RoomDb? = null
        fun getInstance(context: Context): RoomDb {
            if(instance == null) {
                instance = Room.databaseBuilder(
                    context,
                    RoomDb::class.java,
                    "ShoeStringStudioDatabase"
                )
                    .build()
            }
            return instance as RoomDb
        }
    }
}