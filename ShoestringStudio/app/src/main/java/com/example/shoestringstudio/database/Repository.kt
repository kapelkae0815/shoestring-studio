package com.example.shoestringstudio.database

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.shoestringstudio.database.daos.ProjectDao
import com.example.shoestringstudio.database.entities.Project
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class Repository(app: Application) {

    private lateinit var projectDao: ProjectDao
    private lateinit var data: LiveData<List<Project>>
    init{
        val database: RoomDb = RoomDb.getInstance(app)
        if(database != null) {
            projectDao = database.projectDao()
        }


    }
    fun getProjects(id: Long): LiveData<List<Project>> {
        data = projectDao.getProjectsFromUser(id)
        return data
    }

    fun insertProject(project: Project) {
        CoroutineScope(IO).launch{
            projectDao.insertProject(project)
        }
    }
    fun deleteProject(id: Long) {
        CoroutineScope(IO).launch{
            projectDao.deleteProject(projectDao.getIdFromProject(id))
        }
    }

}