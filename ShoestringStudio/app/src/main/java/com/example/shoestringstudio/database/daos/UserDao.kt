package com.example.shoestringstudio.database.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.shoestringstudio.database.entities.User

@Dao
interface UserDao {
    @Insert
    fun insertUser(user: User)

    @Query("Select * FROM User")
    fun getAllUsers(): LiveData<List<User>>



}