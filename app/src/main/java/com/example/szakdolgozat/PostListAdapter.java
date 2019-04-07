package com.example.szakdolgozat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.myapplication.R;

import java.util.ArrayList;

public class PostListAdapter extends ArrayAdapter {

    public PostListAdapter(Context context, ArrayList<Post> posts) {
        super(context, 0, posts);
    }

    public View getView(int position, View convertView, ViewGroup parent){

        Post post=(Post) getItem(position);

        if(!post.getTitleMediaContent().getVideoOrImage()) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.titleimagepostlistviewitem, parent, false);

            TextView dateText = (TextView) convertView.findViewById(R.id.postDateText);
            dateText.setText(post.getDate());

            TextView authoname = (TextView) convertView.findViewById(R.id.postUserNameText);
            authoname.setText(post.getAuthorname());

            TextView commentContent = (TextView) convertView.findViewById(R.id.postTitleText);
            commentContent.setText(post.getTitle());

            ImageView titleImageView=(ImageView)convertView.findViewById(R.id.titleImageView);
            titleImageView.setImageURI(post.getTitleMediaContent().getMediaUri());

       }else if(post.getTitleMediaContent().getVideoOrImage()) {

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.titlevideopostlistviewitem, parent, false);

            TextView dateText = (TextView) convertView.findViewById(R.id.postDateText);
            dateText.setText(post.getDate());

            TextView authoname = (TextView) convertView.findViewById(R.id.postUserNameText);
            authoname.setText(post.getAuthorname());

            TextView commentContent = (TextView) convertView.findViewById(R.id.postTitleText);
            commentContent.setText(post.getTitle());

            VideoView titleVideo=(VideoView)convertView.findViewById(R.id.titleVideoView);
            titleVideo.setVideoURI(post.getTitleMediaContent().getMediaUri());
            titleVideo.seekTo(2);
        }

        GridView mediaGridView=(GridView)convertView.findViewById(R.id.contentGridView);
        mediaGridView.setAdapter(new contentGridViewAdapter(getContext(), post.getPostMediaContent()));

        ListView c=(ListView)convertView.findViewById(R.id.commentItemListView);
        c.setAdapter(new commentListAdapter(getContext(),post.getCommentList()));

        return convertView;
    }
}
