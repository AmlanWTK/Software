package com.example.testingmyentertainment.Videos;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class VideoItemTest {

    @Test
    public void testVideoItemInitialization() {
        String title = "Test Title";
        String thumbnailUrl = "https://example.com/thumbnail.jpg";
        String videoUrl = "https://example.com/video.mp4";

        VideoItem videoItem = new VideoItem(title, thumbnailUrl, videoUrl);

        // Verify that the VideoItem object is initialized correctly
        assertEquals(title, videoItem.title);
        assertEquals(thumbnailUrl, videoItem.thumbnailUrl);
        assertEquals(videoUrl, videoItem.videoUrl);
    }

}
