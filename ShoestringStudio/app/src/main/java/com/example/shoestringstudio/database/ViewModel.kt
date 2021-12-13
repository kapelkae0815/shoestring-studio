package com.example.shoestringstudio.database

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.shoestringstudio.database.entities.Project

class ViewModel(app: Application): AndroidViewModel(app) {
    private val repository: Repository = Repository(app)
    // Get the id inputted by the user to get projects
    private val projects: LiveData<List<Project>> = repository.getProjects(id = 0)


    fun getAllProjects(): LiveData<List<Project>> {
        return projects
    }
}