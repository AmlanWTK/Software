package com.example.testingmyentertainment.Videos;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.GridView;
import androidx.media3.exoplayer.ExoPlayer;
import androidx.media3.ui.PlayerView;
import androidx.media3.common.MediaItem;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.GridView;
import androidx.media3.exoplayer.ExoPlayer;
import androidx.media3.ui.PlayerView;
import androidx.media3.common.MediaItem;

import com.example.testingmyentertainment.R;

public class MainActivity1 extends AppCompatActivity {
    PlayerView playerView;
    ExoPlayer player;
    GridView videoGridView;
    VideoItem[] videoItems = {
            new VideoItem("Video 1", "https://firebasestorage.googleapis.com/v0/b/onlinemusic-9f1a8.appspot.com/o/Image%2Fhighway.jpeg?alt=media&token=382d9b6d-77ae-4e9d-9c45-41dddd4520ee", "https://firebasestorage.googleapis.com/v0/b/onlinemusic-9f1a8.appspot.com/o/Aesthetic%20video%F0%9F%8C%B1%F0%9F%8D%83.mp4?alt=media&token=caeb9965-9ab2-422f-a1db-2a26c001703b"),
            new VideoItem("Video 1", "https://firebasestorage.googleapis.com/v0/b/onlinemusic-9f1a8.appspot.com/o/Image%2Fhighway.jpeg?alt=media&token=382d9b6d-77ae-4e9d-9c45-41dddd4520ee", "https://firebasestorage.googleapis.com/v0/b/onlinemusic-9f1a8.appspot.com/o/Aesthetic%20video%F0%9F%8C%B1%F0%9F%8D%83.mp4?alt=media&token=caeb9965-9ab2-422f-a1db-2a26c001703b"),
            new VideoItem("Video 1", "https://firebasestorage.googleapis.com/v0/b/onlinemusic-9f1a8.appspot.com/o/Image%2Fhighway.jpeg?alt=media&token=382d9b6d-77ae-4e9d-9c45-41dddd4520ee", "https://firebasestorage.googleapis.com/v0/b/onlinemusic-9f1a8.appspot.com/o/Aesthetic%20video%F0%9F%8C%B1%F0%9F%8D%83.mp4?alt=media&token=caeb9965-9ab2-422f-a1db-2a26c001703b"),
            new VideoItem("Video 1", "https://firebasestorage.googleapis.com/v0/b/onlinemusic-9f1a8.appspot.com/o/Image%2Fhighway.jpeg?alt=media&token=382d9b6d-77ae-4e9d-9c45-41dddd4520ee", "https://firebasestorage.googleapis.com/v0/b/onlinemusic-9f1a8.appspot.com/o/Aesthetic%20video%F0%9F%8C%B1%F0%9F%8D%83.mp4?alt=media&token=caeb9965-9ab2-422f-a1db-2a26c001703b")

            // Add more video items as needed
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);

        // Initialize ExoPlayer
        player = new ExoPlayer.Builder(this).build();
        playerView = findViewById(R.id.playerView);
        playerView.setPlayer(player);

        // Setting up GridView with VideoAdapter
        videoGridView = findViewById(R.id.videoGridView);
        VideoAdapter adapter = new VideoAdapter(this, videoItems);
        videoGridView.setAdapter(adapter);

        // GridView item click listener to play video
        videoGridView.setOnItemClickListener((parent, view, position, id) -> {
            VideoItem item = videoItems[position];
            MediaItem mediaItem = MediaItem.fromUri(item.videoUrl);
            player.setMediaItem(mediaItem);
            player.prepare();
            player.play();
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (player != null) {
            player.release(); // Release the player when the activity is destroyed
        }
    }
}
