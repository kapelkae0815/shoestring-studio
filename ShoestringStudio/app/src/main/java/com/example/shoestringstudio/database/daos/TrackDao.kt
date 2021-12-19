package com.example.shoestringstudio.database.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.shoestringstudio.database.entities.SoundSource
import com.example.shoestringstudio.database.entities.Track

@Dao
interface TrackDao {
    @Insert
    fun insertTack(track: Track)

    @Delete
    fun deleteTrack(track: Track)

    @Update
    fun updateTrack(track: Track)

    @Query("SELECT * FROM Track WHERE trackId = :id")
    fun getTrackFromId(id: Long) : Track

//    @Query("select * from soundSource where trackId = :trackId")
//    fun getSoundsFromSoundSource(trackId: Long): LiveData<SoundSource>

    @Query("SELECT max(trackId) FROM Track WHERE projectId = :id")
    fun getLatestTrack(id: Long?): Long

    @Query("SELECT COUNT(*) FROM Track WHERE projectId = :id")
    fun getTrackAmount(id: Long): Int
}