package com.example.shoestringstudio.database.relationships

import androidx.lifecycle.LiveData
import androidx.room.Embedded
import androidx.room.Relation
import com.example.shoestringstudio.database.entities.Project
import com.example.shoestringstudio.database.entities.User

data class UserWithProjects(
    @Embedded val user: User,
    @Relation(
        parentColumn = "id",
        entityColumn = "userId"
    )
    val projects: LiveData<List<Project>>
    )
