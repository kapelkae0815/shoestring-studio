package com.example.shoestringstudio.database

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.shoestringstudio.database.entities.Project
import com.example.shoestringstudio.database.relationships.ProjectWithTracks
import com.example.shoestringstudio.database.relationships.UserWithProjects

class ViewModel(app: Application): AndroidViewModel(app) {
    private val repository = Repository.getInstance(app)
    // Get the id inputted by the user to get projects
    private lateinit var projects: LiveData<List<UserWithProjects>>
    private lateinit var tracks: LiveData<ProjectWithTracks>


    fun getAllProjects(id: Long): LiveData<List<UserWithProjects>> {
        projects = repository.getProjects(id)
        return projects
    }
    fun getAllTracks(id: Long): LiveData<ProjectWithTracks> {
        tracks = repository.getTracks(id)
        return tracks
    }
}