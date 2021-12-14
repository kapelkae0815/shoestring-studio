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
    private lateinit var tracks: LiveData<List<ProjectWithTracks>>

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

    fun insertProject(project: Project): Long? {
        var id: Long? = 0
        CoroutineScope(IO).launch {
            projectDao.insertProject(project)
            id = projectDao.getLatestProjectId()
        }

        return id
    }

    fun deleteProject(id: Long) {
        CoroutineScope(IO).launch {
            while (trackDao.getTrackAmount(id) > 0) {
                trackDao.deleteTrack(trackDao.getTrackFromId(trackDao.getLatestTrack(id)))
            }
            projectDao.deleteProject(projectDao.getProjectFromId(id))
        }
    }

    fun updateProject(project: Project) {
        CoroutineScope(IO).launch {
            projectDao.updateProject(project)
        }
    }


    // track functions
    fun insertTrack(track: Track) {
        fun insertTrack(id: Long) {
            CoroutineScope(IO).launch {
                trackDao.insertTack(track)
                trackDao.insertTack(Track(null, 100, 100, 100, 0, id, 0))
            }
        }
    }


    fun getTracks(id: Long): LiveData<List<ProjectWithTracks>> {
        tracks = projectDao.getTracksFromProject(id)
        return tracks
    }

    fun deleteTrack(id: Long) {
        CoroutineScope(IO).launch {
            trackDao.deleteTrack(trackDao.getTrackFromId(id))
            val trackAmount = trackDao.getTrackAmount(id)
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


