package com.example.test_app_basketball;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Video extends AppCompatActivity {
    private VideoView videoView;
    Intent GPS, Video, Registration;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        Registration = new Intent(this, Registration.class);
        Video = new Intent(this, Video.class);
        GPS = new Intent(this, GPS_Cordinates.class);
    }
    public void Start(View view) {
        String videoUrl = "https://www.learningcontainer.com/wp-content/uploads/2020/05/sample-mp4-file.mp4";
        videoView = findViewById(R.id.videoView);
        Uri uri = Uri.parse(videoUrl);

        videoView.setVideoURI(uri);
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        mediaController.setMediaPlayer(videoView);
        videoView.setMediaController(mediaController);
        videoView.start();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        String st = item.getTitle().toString();

        if (st.equals("Registration")) {
            startActivity(Registration);
        } else if (st.equals("Video")) {
            startActivity(Video);
        } else if (st.equals("GPS_SPOT")) {
            startActivity(GPS);
        } else if(st.equals("Sign_Places")){
             Intent intent = new Intent(this, Sign_Places.class);
             startActivity(intent);
        }else if(st.equals("Signs2")){
             Intent intent = new Intent(this, Signs2.class);
             startActivity(intent);
        }

        return super.onContextItemSelected(item);
    }

}