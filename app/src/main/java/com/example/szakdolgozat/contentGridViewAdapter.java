package com.example.szakdolgozat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.VideoView;

import com.example.myapplication.R;

import java.util.ArrayList;

public class contentGridViewAdapter extends ArrayAdapter {
    public contentGridViewAdapter(Context context, ArrayList<media> media) {
        super(context, 0, media);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        media mediaItem = (media) getItem(position);

        if (!mediaItem.getVideoOrImage()) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.imagelistitem, parent, false);
            ImageView gridImage = (ImageView) convertView.findViewById(R.id.imageViewItem);
            gridImage.setImageURI(mediaItem.getMediaUri());
        } else if (mediaItem.getVideoOrImage()) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.videolistitem, parent, false);
            VideoView videoItem = (VideoView) convertView.findViewById(R.id.itemvideoView);
            videoItem.setVideoURI(mediaItem.getMediaUri());
            videoItem.seekTo(2);
        }
        return convertView;
    }
}
