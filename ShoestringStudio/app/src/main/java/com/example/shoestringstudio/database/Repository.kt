package com.example.shoestringstudio.database

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.shoestringstudio.database.daos.ProjectDao
import com.example.shoestringstudio.database.daos.TrackDao
import com.example.shoestringstudio.database.daos.UserDao
import com.example.shoestringstudio.database.entities.Project
import com.example.shoestringstudio.database.entities.Track
import com.example.shoestringstudio.database.relationships.ProjectWithTracks
import com.example.shoestringstudio.database.relationships.UserWithProjects
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class Repository(app: Application) {
    private lateinit var projectDao: ProjectDao
    private lateinit var userDao: UserDao
    private lateinit var trackDao: TrackDao
    private lateinit var data: LiveData<List<UserWithProjects>>
    private lateinit var projects: LiveData<List<UserWithProjects>>
    private lateinit var tracks: LiveData<ProjectWithTracks>


    init {
        val database: RoomDb? = RoomDb.getInstance(app)
        if (database != null) {
            projectDao = database.projectDao()
            userDao = database.userDao()
            trackDao = database.trackDao()
        }

        projects = userDao.getProjectsFromUser(0)
    }

    fun getProjects(id: Long): LiveData<List<UserWithProjects>> {
        data = userDao.getProjectsFromUser(0)
        projects = userDao.getProjectsFromUser(0)

        return data
        return projects
    }


    // Project functions

    fun insertProject(project: Project) {
        CoroutineScope(IO).launch {
            projectDao.insertProject(project)
        }
    }

    fun deleteProject(id: Long) {
        CoroutineScope(IO).launch {
//            while (trackDao.getTrackAmount(id) > 0) {
//                trackDao.deleteTrack(trackDao.getTrackFromId(trackDao.getLatestTrack(id)))
//            }
            projectDao.deleteProject(projectDao.getProjectFromId(id))
        }
    }

    fun updateProject(project: Project) {
        CoroutineScope(IO).launch {
            projectDao.updateProject(project)
        }
    }


    // track functions
    fun insertTrack(id: Long, filePath: String?, name: String?) {
        CoroutineScope(IO).launch {
            trackDao.insertTrack(Track(null, name, 100, 100, 0, id, filePath!!))
        }
    }


    fun getTracks(id: Long): LiveData<ProjectWithTracks> {
        tracks = projectDao.getTracksFromProject(id)
        return tracks
    }

    fun deleteTrack(id: Long) {
        CoroutineScope(IO).launch {
            trackDao.deleteTrack(trackDao.getTrackFromId(id))
            projectDao.updateTrackAmount(id)
        }
    }

    fun getTrackAmount(id: Long): Int {
        var count = -1
        CoroutineScope(IO).launch {
            count = trackDao.getTrackAmount(id)
        }
        return count
    }

    // sound functions


    companion object {
        private var instance: Repository? = null
        fun getInstance(app: Application): Repository {
            if (instance == null) instance = Repository(app)
            return instance as Repository
        }

    }
}


