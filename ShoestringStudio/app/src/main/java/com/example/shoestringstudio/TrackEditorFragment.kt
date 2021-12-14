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
import androidx.core.net.toUri
import androidx.navigation.fragment.navArgs
import com.example.shoestringstudio.database.Repository
import com.example.shoestringstudio.database.entities.Track


class TrackEditorFragment : Fragment() {

    private lateinit var binding: FragmentTrackEditorBinding
    private val args: TrackEditorFragmentArgs by navArgs()
    //make an ArrayList of files that will be the project
    var tracks = ArrayList<File>()
    var tracksPlayer = ArrayList<MediaPlayer>()
    var trackPlayer = MediaPlayer()
    var recyclerLayout = LinearLayoutManager(context)
    var recyclerAdapter = TrackEditorAdapter(tracks)
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
            repository.insertTrack(Track(null, 100, 100, 100, 0, args.projectId, 0))
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

        binding.trackDisplay.apply{
            setHasFixedSize(true)
            // layout manager
            binding.trackDisplay.layoutManager = LinearLayoutManager(context)
            // adapter
            binding.trackDisplay.adapter = recyclerAdapter

        }

        setHasOptionsMenu(true)

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
                    tracks.add(track)
                    trackPlayer = MediaPlayer.create(context,audioUri)
                    tracksPlayer.add(trackPlayer)
                    //setUpMediaPlayer()
                    //repository.insertTrack(Track(null, 100, 100, 100, 0, args.projectId, 0))
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

    private fun setUpMediaPlayer(){
        //add each Uri into a mediaPlayer and then add the media players to an arrayList
        for(i in tracks.indices){
            trackPlayer = MediaPlayer.create(context,tracks[i].toUri())
            tracksPlayer.add(trackPlayer)
      }
    }
}