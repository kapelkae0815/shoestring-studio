package com.example.shoestringstudio;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import zeroonezero.android.audio_mixer.AudioMixer;
import zeroonezero.android.audio_mixer.input.AudioInput;
import zeroonezero.android.audio_mixer.input.GeneralAudioInput;

public class MergeAudio extends AppCompatActivity {
    ArrayList<AudioInput> inputs = new ArrayList<AudioInput>();
    // Storage Permissions
    public void mix(ArrayList<File> tracks) throws IOException {
        //create an ArrayList of Inputs


        //turn every track into a GeneralAudioInput and then add them to the ArrayList
        for(int i = 0; i < tracks.size(); i++) {
            String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC).getPath() + "/"+tracks.get(i).getName();
            AudioInput input = new GeneralAudioInput(path);
            inputs.add(input);
        }

        //make an output path for the AudioMixer
        String outputPath = "/sdcard/Music/" +"audio_mixer_outputAnotherOne.mp3";
        final AudioMixer audioMixer = new AudioMixer(outputPath);
        for(int i = 0; i < tracks.size(); i++) {
            audioMixer.addDataSource(inputs.get(i));
        }

        //setup the AudioMixer and run it to combine inputs
        audioMixer.setSampleRate(44100);
        audioMixer.setBitRate(128000);
        audioMixer.setChannelCount(2);
        audioMixer.start();
        audioMixer.processSync();
        audioMixer.release();
    }

    public void volume(int vol) {
        for(int i = 0; i < inputs.size(); i++){
            inputs.get(i).setVolume(vol);
        }
    }

}
