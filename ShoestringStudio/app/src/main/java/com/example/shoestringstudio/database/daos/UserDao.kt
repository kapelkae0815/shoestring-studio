package com.example.shoestringstudio.database.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.example.shoestringstudio.database.entities.Project
import com.example.shoestringstudio.database.entities.User
import com.example.shoestringstudio.database.relationships.UserWithProjects

@Dao
interface UserDao {
    @Insert
    fun insertUser(user: User)

    @Query("Select * FROM User")
    fun getAllUsers(): LiveData<List<User>>

    @Insert
    fun updateUserList(project: Project)

    @Transaction
    @Query("SELECT * FROM Project WHERE userId = :id")
    fun getProjectsFromUser(id: Long): LiveData<List<UserWithProjects>>

}