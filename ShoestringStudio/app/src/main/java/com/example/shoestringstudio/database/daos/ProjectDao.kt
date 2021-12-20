package com.example.shoestringstudio.database.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.shoestringstudio.database.entities.Project
import com.example.shoestringstudio.database.relationships.ProjectWithTracks

@Dao
interface ProjectDao {
    @Delete
    fun deleteProject(project: Project)

    @Update
    fun updateProject(project: Project)

    @Insert
    fun insertProject(project: Project)

    @Query("UPDATE Project SET trackAmount = :trackAmount WHERE projectId = :id")
    fun updateTrackAmount(id: Long?, trackAmount: Int)

    @Query("SELECT * FROM Project WHERE projectId = :id")
    fun getProjectFromId(id: Long): Project


    @Transaction
    @Query("SELECT * FROM Track WHERE projectId = :id")
    fun getTracksFromProject(id: Long?): LiveData<ProjectWithTracks>

    @Query("SELECT max(projectId) FROM Project")
    fun getLatestProjectId(): Long

    @Query("SELECT COUNT(*) FROM TRACK WHERE projectId = :id")
    fun getTrackAmount(id: Long?): Int


}