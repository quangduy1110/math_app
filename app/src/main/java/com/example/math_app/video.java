package com.example.math_app;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class video extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video);
        VideoView videoView = findViewById(R.id.videoview);
        String videoPath = getIntent().getStringExtra("videoPath");
        videoView.setVideoURI(Uri.parse(videoPath));
        videoView.start();
        MediaController mediaController = new MediaController(this);
       videoView.setMediaController(mediaController);
       mediaController.setAnchorView(videoView);

    }
}