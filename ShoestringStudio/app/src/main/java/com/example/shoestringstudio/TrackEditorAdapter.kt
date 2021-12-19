package com.example.shoestringstudio

import android.app.Application
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.ImageButton
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.shoestringstudio.database.Repository
import com.example.shoestringstudio.database.relationships.ProjectWithTracks
import com.example.shoestringstudio.databinding.FragmentTrackEditorBinding
import java.io.File

/**
 * This Adapter takes in the list of audio files and adds a new track layout
 * to the recycler view after a new audiofile is imported
 */
class TrackEditorAdapter(files: ArrayList<File>?):
        RecyclerView.Adapter<com.example.shoestringstudio.TrackEditorAdapter.TrackHolder>(){
        private lateinit var context: Context
        private var tracks: List<ProjectWithTracks>? = null

        class TrackHolder(val trackView: View): RecyclerView.ViewHolder(trackView)
        fun setTracks(track: List<ProjectWithTracks>?) {
                tracks = track
        }
        var filesList = files

        //create the viewHolder for tracks
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackHolder{
                val view = (LayoutInflater.from(parent.context).inflate(R.layout.layout_track, parent, false))
                context = parent.context
                return com.example.shoestringstudio.TrackEditorAdapter.TrackHolder(view)
        }

        //Bind the viewHolder
        override fun onBindViewHolder(holder: com.example.shoestringstudio.TrackEditorAdapter.TrackHolder, position: Int) {
                val current = filesList?.get(position)?.nameWithoutExtension
                val trackCurrent = tracks?.get(position)?.tracks?.get(position)
                val nameView = holder.trackView.findViewById<TextView>(R.id.textTrackName)
                val buttonDelete = holder.trackView.findViewById<ImageButton>(R.id.buttonDelete)
                if (current != null) {
                        nameView.text = current
//                        buttonDelete.setOnClickListener{
//                                Repository.getInstance(context.applicationContext as Application)
//                                        .deleteTrack(trackCurrent?.projectId!!)
//                        }
                }


        }

        //get the size of the file list
        override fun getItemCount(): Int {
                return filesList?.size?:0
        }
}