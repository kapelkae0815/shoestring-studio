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

    // Storage Permissions

    public void mix(ArrayList<File> tracks) throws IOException {
        Log.d("myTag", "Inside mix");
        ArrayList<AudioInput> inputs = new ArrayList<AudioInput>();
        for(int i = 0; i < tracks.size(); i++) {
            String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC).getPath() + "/"+tracks.get(i).getName();
            AudioInput input = new GeneralAudioInput(path);
            inputs.add(input);
        }

        String outputPath = "/sdcard/Music/" +"audio_mixer_output.mp3";
        final AudioMixer audioMixer = new AudioMixer(outputPath);
        for(int i = 0; i < tracks.size(); i++) {
            audioMixer.addDataSource(inputs.get(i));
        }
        audioMixer.setSampleRate(44100);
        audioMixer.setBitRate(128000);
        audioMixer.setChannelCount(tracks.size());
        audioMixer.start();
        audioMixer.processSync();
        audioMixer.release();
        Log.d("myTag", "AudioMixer released");
    }

}
