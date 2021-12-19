package com.example.shoestringstudio

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import com.example.shoestringstudio.databinding.FragmentTrackEditorBinding
import androidx.activity.result.ActivityResultCaller
import androidx.activity.result.contract.ActivityResultContracts
import java.io.File
import java.util.*

import android.widget.PopupMenu
import androidx.core.view.get
import androidx.navigation.findNavController
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import android.media.MediaPlayer
import android.provider.MediaStore
import android.util.Log
import androidx.core.net.toUri
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.shoestringstudio.database.Repository
import com.example.shoestringstudio.database.ViewModel
import com.example.shoestringstudio.database.entities.Track
import com.example.shoestringstudio.database.relationships.ProjectWithTracks
import com.example.shoestringstudio.database.relationships.UserWithProjects


class TrackEditorFragment : Fragment() {

    private lateinit var binding: FragmentTrackEditorBinding
    private val args: TrackEditorFragmentArgs by navArgs()
    //make an ArrayList of files that will be the project
    var tracks = ArrayList<File>()
    var tracksPlayer = ArrayList<MediaPlayer>()
    lateinit var trackPlayer: MediaPlayer
    lateinit var recyclerAdapter: TrackEditorAdapter
    private lateinit var viewModel: ViewModel
    private lateinit var repository: Repository


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

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

    //Shows a popup to choose between recording or
    // selecting a sound source from the file system
    fun showPopup(v: View?) {
        val popup: PopupMenu = PopupMenu(activity, v)
        val inflater: MenuInflater = popup.getMenuInflater()
        inflater.inflate(R.menu.add_track_menu, popup.getMenu())
        popup.setOnMenuItemClickListener { item: MenuItem ->
            if (item.itemId == R.id.choose_from_storage) {
                prepOpenAudioFiles()
            } else if (item.itemId == R.id.record_new_track) {
                toRecordTrack(v)
            }
            true
        }
        popup.show()
    }

    //goes to RecordingFragment
    fun toRecordTrack(v: View?){
        for(i in tracksPlayer) i.release()
        v?.findNavController()?.navigate(TrackEditorFragmentDirections.actionTrackEditorFragmentToRecordingFragment())
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
                    //tracks.add(track)
                    //setUpMediaPlayer()
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

            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun compileForExport(tracks: List<File>) {

    }

    private fun sendProject(){
        val sendIntent: Intent = Intent().apply {
            type = "audio/*"
            action = Intent.ACTION_SEND
        }
        //sendIntent.putExtra(Intent.EXTRA_STREAM, audioFile);
        startActivity(Intent.createChooser(sendIntent, "Share Image"))
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
                if(t != null) {
                    adapter.setTracks(t)
                    for(i in t.tracks) {
                        if(i.pathName?.toUri() != null) {
                            Log.i("TEST: ", Uri.parse(i.pathName).toString())
                            tracks.add(File(Uri.parse(i.pathName).path))
                            trackPlayer = MediaPlayer.create(context, Uri.parse(i.pathName))
                            tracksPlayer.add(trackPlayer)

                        }
                    }
                }
            }
        })
    }

    private fun setUpMediaPlayer(){
        //add each Uri into a mediaPlayer and then add the media players to an arrayList
        for(i in tracks.indices){
            trackPlayer = MediaPlayer.create(context,tracks[i].toUri())
            tracksPlayer.add(trackPlayer)
      }
    }
}