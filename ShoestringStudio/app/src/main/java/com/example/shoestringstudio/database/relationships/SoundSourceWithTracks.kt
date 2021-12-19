package com.example.shoestringstudio.database.relationships

import androidx.lifecycle.LiveData
import androidx.room.Embedded
import androidx.room.Relation
import com.example.shoestringstudio.database.entities.SoundSource
import com.example.shoestringstudio.database.entities.Track

data class SoundSourceWithTracks(
    @Embedded val soundSource: SoundSource,
    @Relation(
        parentColumn = "soundId",
        entityColumn = "soundId"
    )
    val tracks: List<Track>
)
