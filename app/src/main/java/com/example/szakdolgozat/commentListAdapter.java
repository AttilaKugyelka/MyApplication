package com.example.szakdolgozat;

import android.annotation.SuppressLint;
import android.arch.core.executor.TaskExecutor;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;

import java.util.ArrayList;

public class commentListAdapter extends ArrayAdapter {
    public commentListAdapter(Context context, ArrayList<Comment> commentList) {
        super(context,0, commentList);
    }

    public View getView(int position, View convertView, ViewGroup parent){
        Comment comment=(Comment) getItem(position);

        convertView = LayoutInflater.from(getContext()).inflate(R.layout.commentlistitem, parent, false);
//        Toast.makeText(getContext(), convertView.getWidth(), Toast.LENGTH_LONG).show();
        TextView commentTextview=(TextView)convertView.findViewById(R.id.commentItemText);
        commentTextview.setText(comment.getComment());

        TextView commentAuthorTextView=(TextView)convertView.findViewById(R.id.commentItemAuthor);
        commentAuthorTextView.setText(comment.getCommentAuthorName());

        TextView commentDateTExt=(TextView)convertView.findViewById(R.id.commentItemDate);
        commentDateTExt.setText(comment.getCommentDate());

        return convertView;
    }
}
