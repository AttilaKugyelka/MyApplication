package com.example.szakdolgozat;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Post {

    private Date commentDate=null;
    private String authorname="";
    private String title="";
    private Boolean Like=false;
    private ArrayList<media> postMediaContent=null;
    private media titleMediaContent=null;
    private ArrayList<Comment> commentek=null;



    public Post(media titleMedia){
        commentDate=Calendar.getInstance().getTime();
        postMediaContent=new ArrayList<>();
        commentek=new ArrayList<>();
        titleMediaContent=titleMedia;
    }

    public String getDate(){
        DateFormat dateFormat=new SimpleDateFormat("yyyy.MM.dd");
        return dateFormat.format(commentDate);
    }

    public void AddMediaToGrid(media tempMedia){
        postMediaContent.add(tempMedia);
    }

    public void AddCommentToList(Comment comment){
        commentek.add(comment);
    }

    public ArrayList<Comment> getCommentList(){
        return commentek;
    }

    public void setTitle(String title){
        this.title=title;
    }

    public String getTitle(){
        return this.title;
    }

    public void setAuthorname(String authorname){
        this.authorname=authorname;
    }

    public String getAuthorname(){
        return this.authorname;
    }

    public void setTitleMediaContent(media mediaContent){
        this.titleMediaContent=titleMediaContent;
    }

    public media getTitleMediaContent(){
        return this.titleMediaContent;
    }

    public void setPostMediaContent(ArrayList<media> postMediaContent){
        this.postMediaContent=postMediaContent;
    }

    public ArrayList<media> getPostMediaContent(){
        return this.postMediaContent;
    }
}