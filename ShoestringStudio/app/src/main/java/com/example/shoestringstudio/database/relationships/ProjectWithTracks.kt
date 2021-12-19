package com.example.shoestringstudio.database.relationships

import androidx.lifecycle.LiveData
import androidx.room.Embedded
import androidx.room.Relation
import com.example.shoestringstudio.database.entities.Project
import com.example.shoestringstudio.database.entities.Track

data class ProjectWithTracks (
    @Embedded val project: Project,
    @Relation(
        parentColumn = "projectId",
        entityColumn = "projectId",
    )
    val tracks: List<Track>
)