package com.example.shoestringstudio.database.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.shoestringstudio.database.entities.Project

@Dao
interface ProjectDao {
    @Insert
    fun insertProject(project: Project)

    @Delete
    fun deleteProject(project: Project)

    @Query("SELECT * FROM Project WHERE userId = :id")
    fun getProjectsFromUser(id: Long): LiveData<List<Project>>

    @Query("SELECT * FROM Project WHERE projectId = :id")
    fun getIdFromProject(id: Long): Project



}