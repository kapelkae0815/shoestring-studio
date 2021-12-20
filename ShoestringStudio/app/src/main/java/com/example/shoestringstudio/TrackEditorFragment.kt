package com.example.shoestringstudio

import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.*
import android.widget.PopupMenu
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.net.toUri
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shoestringstudio.database.Repository
import com.example.shoestringstudio.database.ViewModel
import com.example.shoestringstudio.database.relationships.ProjectWithTracks
import com.example.shoestringstudio.databinding.FragmentTrackEditorBinding
import java.io.File
import java.lang.NullPointerException
import java.util.*


class TrackEditorFragment : Fragment() {

    private lateinit var binding: FragmentTrackEditorBinding
    private val args: TrackEditorFragmentArgs by navArgs()

    var tracks = ArrayList<File>()
    var tracksPlayer = ArrayList<MediaPlayer>()
    var trackPlayer = MediaPlayer()
    var recyclerLayout = LinearLayoutManager(context)
    var recyclerAdapter = TrackEditorAdapter()
    private lateinit var viewModel: ViewModel

    var fileOut = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC), "test/mp4")
    private lateinit var repository: Repository

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //fileOut.createNewFile()
        repository = Repository.getInstance(activity?.application!!)
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate<FragmentTrackEditorBinding>(
            inflater, R.layout.fragment_track_editor, container, false
        )
        //button that opens the popup menu
        binding.buttonAddTrack.setOnClickListener { v: View ->
            showPopup(binding.buttonAddTrack)
        }

        binding.buttonPlayPause.setOnClickListener { v: View ->
            for(i in tracksPlayer.indices){
                if(tracksPlayer[i].isPlaying){
                    tracksPlayer[i].pause()
                }
                else{
                    tracksPlayer[i].start()
                }
            }
        }

        setHasOptionsMenu(true)
        Log.i("id: " , args.projectId.toString())
        getTracks()
        return binding.root
    }

    /**
     * Shows a popup to choose between recording or
     * selecting a sound source from the file system
     */
    private fun showPopup(v: View?) {
        val popup: PopupMenu = PopupMenu(activity, v)
        val inflater: MenuInflater = popup.getMenuInflater()
        inflater.inflate(R.menu.add_track_menu, popup.getMenu())
        popup.setOnMenuItemClickListener { item: MenuItem ->
            if (item.itemId == R.id.choose_from_storage) {
                prepOpenAudioFiles()
            }
            true
        }
        popup.show()
    }



    /**
     * The Intent for opening the Audio file explorer
     */
    private fun prepOpenAudioFiles() {
        val intent: Intent = Intent().apply {
            type = "audio/*"
            action = Intent.ACTION_GET_CONTENT
        }
        //if (intent.resolveActivity(packageManager) != null) {
        audioFileLauncher.launch(intent)
        //}
    }

    /**
     * Launches the intent after making sure the intent is giving back data,
     * also notifies the Adpter of a new file being added
     */
    private var audioFileLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.data != null) {
                if (result.data!!.data != null) {
                    //store the audio file in a variable
                    val audioUri = result.data!!.data as Uri
                    val track = File(audioUri.path)
                    tracks.add(track)
                    repository.insertTrack(args.projectId!!, audioUri.toString(), track.nameWithoutExtension)

                    recyclerAdapter.notifyDataSetChanged()
                }
            }
        }



    //Inflate the Options menu
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.option_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item?.itemId) {
            R.id.exportToDevice -> {
                compileForExport(tracks)
            }
            R.id.shareProject -> {

            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun compileForExport(tracks: List<File>) {
        val mergeAudio : MergeAudio? = null
        mergeAudio?.mix(tracks)
    }

    private fun setUpMediaPlayer(){
        //add each Uri into a mediaPlayer and then add the media players to an arrayList
        for(i in tracks.indices){
            trackPlayer = MediaPlayer.create(context,tracks[i].toUri())
            tracksPlayer.add(trackPlayer)
        }
    }

    /**
     * Holds observer for getting the recyclerView
     * Displays all tracks through the database
     * Does break when restarting app
     */
    private fun getTracks() {
        recyclerAdapter = TrackEditorAdapter()
        binding.trackDisplay.apply {
            setHasFixedSize(true)
            binding.trackDisplay.layoutManager = LinearLayoutManager(context)
            binding.trackDisplay.adapter = recyclerAdapter
        }
        val viewModelProvider = ViewModelProvider(this)
        viewModel = viewModelProvider.get(ViewModel::class.java)
        viewModel.getAllTracks(args.projectId).observe(viewLifecycleOwner, object:
            Observer<ProjectWithTracks> {
            private var adapter = recyclerAdapter
            override fun onChanged(t: ProjectWithTracks?) {
                tracksPlayer.clear()
                if(t != null) {
                    adapter.setTracks(t)

                    for(i in t.tracks) {
                        tracks.add(File(Uri.parse(i.pathName).path))
                        adapter.notifyDataSetChanged()
                        try{
                            trackPlayer = MediaPlayer.create(context, Uri.parse(i.pathName))
                            tracksPlayer.add(trackPlayer)
                        }
                        catch(e: NullPointerException){
                            trackPlayer.reset()
                            //trackPlayer.setDataSource(i.pathName)
                        }

                    }
                }
            }
        })
    }
}