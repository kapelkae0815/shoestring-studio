package com.example.shoestringstudio;

import androidx.fragment.app.Fragment;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class shoeStringStudioJNI {

    ArrayList<File> tracks = new ArrayList<File>();

    static {
        System.loadLibrary("native");
    }

    public static void main(String[] args) {

    }

    public ArrayList<File> getFiles(){
        return tracks;
    }

    public void setFiles(ArrayList<File> importedTracks){
        tracks = importedTracks;
    }


    // Declare a native method sayHello() that receives no arguments and returns void
}
