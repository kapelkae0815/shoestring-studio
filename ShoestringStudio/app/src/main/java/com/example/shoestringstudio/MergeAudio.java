package com.example.shoestringstudio;

import android.os.Bundle;
import android.os.Environment;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import zeroonezero.android.audio_mixer.AudioMixer;
import zeroonezero.android.audio_mixer.input.AudioInput;
import zeroonezero.android.audio_mixer.input.GeneralAudioInput;

public class MergeAudio extends AppCompatActivity {

    public void mix(List<File> tracks) throws IOException {
        ArrayList<AudioInput> inputs = new ArrayList<AudioInput>();
        for(int i = 0; i < tracks.size(); i++) {
            AudioInput input = new GeneralAudioInput(tracks.get(i).getPath().toString());
            inputs.add(input);
        }


        String outputPath = "/sdcard/Music" +"audio_mixer_output.mp3";

        final AudioMixer audioMixer = new AudioMixer(outputPath);
        for(int i = 0; i < tracks.size(); i++) {
            audioMixer.addDataSource(inputs.get(i));
        }
        audioMixer.setSampleRate(44100);
        audioMixer.setBitRate(128000);
        audioMixer.setChannelCount(tracks.size());
        audioMixer.release();
        
        Toast.makeText(this,"Saved to Storage", Toast.LENGTH_SHORT).show();
    }

}
