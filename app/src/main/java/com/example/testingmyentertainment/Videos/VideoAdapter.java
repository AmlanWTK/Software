package com.example.testingmyentertainment.Videos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.testingmyentertainment.R;
import com.squareup.picasso.Picasso; // Using Picasso for image loading

public class VideoAdapter extends ArrayAdapter<VideoItem> {
    private final Context context;
    private final VideoItem[] videoItems;

    public VideoAdapter(Context context, VideoItem[] videoItems) {
        super(context, R.layout.video_item, videoItems);
        this.context = context;
        this.videoItems = videoItems;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.video_item, parent, false);
            holder = new ViewHolder();
            holder.thumbnail = convertView.findViewById(R.id.videoThumbnail);
            holder.title = convertView.findViewById(R.id.videoTitle);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        VideoItem item = videoItems[position];
        holder.title.setText(item.title);
        Picasso.get().load(item.thumbnailUrl).into(holder.thumbnail); // Load and display the thumbnail

        return convertView;
    }

    static class ViewHolder {
        ImageView thumbnail;
        TextView title;
    }
}
