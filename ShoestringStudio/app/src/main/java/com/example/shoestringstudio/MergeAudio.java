package com.example.shoestringstudio;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.github.hiteshsondhi88.libffmpeg.ExecuteBinaryResponseHandler;
import com.github.hiteshsondhi88.libffmpeg.FFmpeg;
import com.github.hiteshsondhi88.libffmpeg.FFmpegExecuteResponseHandler;
import com.github.hiteshsondhi88.libffmpeg.FFmpegLoadBinaryResponseHandler;
import com.github.hiteshsondhi88.libffmpeg.exceptions.FFmpegCommandAlreadyRunningException;
import com.github.hiteshsondhi88.libffmpeg.exceptions.FFmpegNotSupportedException;

import java.io.File;
import java.util.List;

public class MergeAudio extends AppCompatActivity {
    FFmpeg ffmpeg;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_main_menu);
        try{
            loadFFmpegLibrary();
        }
        catch(FFmpegNotSupportedException e){
            e.printStackTrace();
        }
    }


    public void loadFFmpegLibrary() throws FFmpegNotSupportedException {
        if(ffmpeg == null){
            ffmpeg = FFmpeg.getInstance(this);

            ffmpeg.loadBinary(new FFmpegLoadBinaryResponseHandler() {
                @Override
                public void onFailure() {
                    Toast.makeText(getApplicationContext(), "Failed to load library", Toast.LENGTH_LONG).show();
                }

                @Override
                public void onSuccess() {
                    Toast.makeText(getApplicationContext(), "Successfully loaded library", Toast.LENGTH_LONG).show();
                }

                @Override
                public void onStart() {

                }

                @Override
                public void onFinish() {

                }
            });
        }

    }

    public void executeCommand(String[] command) throws FFmpegCommandAlreadyRunningException {
        ffmpeg.execute(command, new FFmpegExecuteResponseHandler() {
            @Override
            public void onSuccess(String message) {
            }

            @Override
            public void onProgress(String message) {
            }

            @Override
            public void onFailure(String message) {

            }

            @Override
            public void onStart() {

            }

            @Override
            public void onFinish() {

            }
        });
    }

    public void merge(List<File> tracks) throws FFmpegCommandAlreadyRunningException {
        String cmd = "ffmpeg";
        File output = new File("/root/sdcard/music");
        for(int i = 0; i < tracks.size(); i++) {
          cmd += " -i " + tracks.get(i).getName();
        }

        cmd += "-filter_complex amix=inputs="+(String.valueOf(tracks.size() - 1))
                +":duration=first:dropout_transition="+(String.valueOf(tracks.size() - 1))+"output";

        //String []cmd = "ffmpeg -i INPUT1 -i INPUT2 -i INPUT3 -filter_complex amix=inputs=3:duration=first:dropout_transition=3 OUTPUT"
        String[] cmdList = new String[1];
        cmdList[0] = cmd;
        executeCommand(cmdList);
    }

}
