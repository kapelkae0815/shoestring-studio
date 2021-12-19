package com.example.shoestringstudio.database

import android.content.Context
import androidx.room.*
import com.example.shoestringstudio.database.daos.ProjectDao
import com.example.shoestringstudio.database.daos.TrackDao
import com.example.shoestringstudio.database.daos.UserDao
import com.example.shoestringstudio.database.entities.Project
import com.example.shoestringstudio.database.entities.Track
import com.example.shoestringstudio.database.entities.User

@Database(entities = [Project::class,
    Track::class, User::class], version = 1)
@TypeConverters(Converter::class)
abstract class RoomDb: RoomDatabase() {
    abstract fun projectDao(): ProjectDao
    abstract fun trackDao(): TrackDao
    abstract fun userDao(): UserDao


    companion object {
        private var instance: RoomDb? = null
        fun getInstance(context: Context): RoomDb {
            // uncomment below to reset the database
            // note: this is wipe out all of the data inside of the database
            // if you don't want that to happen, then just change the version number
            //context.deleteDatabase("ShoeStringStudioDatabase")
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