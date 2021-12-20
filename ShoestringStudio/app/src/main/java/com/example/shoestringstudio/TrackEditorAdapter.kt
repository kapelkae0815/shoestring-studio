package com.example.shoestringstudio

import android.app.Application
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.ImageButton
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.shoestringstudio.database.Repository
import com.example.shoestringstudio.database.entities.Track
import com.example.shoestringstudio.database.relationships.ProjectWithTracks
import com.example.shoestringstudio.databinding.FragmentTrackEditorBinding
import java.io.File

/**
 * Adapter for the trackEditor Fragment
 * @property context for use of context
 * @property tracks for list of tracks in recyclerView
 */
class TrackEditorAdapter():
        RecyclerView.Adapter<TrackEditorAdapter.TrackHolder>(){
        private lateinit var context: Context
        private var tracks: List<Track>? = null

        class TrackHolder(val trackView: View): RecyclerView.ViewHolder(trackView)

        /**
         * Notifying if recyclerView changes and then updating tracks
         */
        fun setTracks(track: ProjectWithTracks?) {
                if (track != null) {
                        tracks = track.tracks
                }
                notifyDataSetChanged()
        }

        /**
         * initializing viewHolder
         * @returns trackHolder
         */
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackHolder{
                val view = (LayoutInflater.from(parent.context).inflate(R.layout.layout_track, parent, false))
                context = parent.context
                return TrackHolder(view)
        }

        /**
         * binding the viewHolder properties for each index of the recyclerView
         */
        override fun onBindViewHolder(holder: TrackHolder, position: Int) {
                val trackCurrent = tracks?.get(position)
                Log.i("id: ", trackCurrent?.trackId.toString())
                val nameView = holder.trackView.findViewById<TextView>(R.id.textTrackName)
                val buttonDelete = holder.trackView.findViewById<ImageButton>(R.id.buttonDelete)
                if (trackCurrent != null) {
                        nameView.text = trackCurrent?.name
                        buttonDelete.setOnClickListener{
                                Repository.getInstance(context.applicationContext as Application)
                                        .deleteTrack(trackCurrent?.trackId!!)
                        }
                }

        }

        /**
         * @return getting count of recyclerView items/tracks
         */
        override fun getItemCount(): Int {

                return tracks?.size?:0
        }
}