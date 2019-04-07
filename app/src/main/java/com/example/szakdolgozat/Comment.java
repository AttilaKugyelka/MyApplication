package com.example.szakdolgozat;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Comment {

    private String comment="";
    private String commentDate=null;
    private String commentAuthorName="";

    public Comment(){
        DateFormat df=new SimpleDateFormat("YYYY.MM.dd - HH.mm");
        commentDate=df.format(Calendar.getInstance().getTime());
    }

    public void setCommentText(String commentLocal){
        comment=commentLocal;
    }

    public String getComment(){
        return comment;
    }

    public String getCommentDate(){
        return this.commentDate;
    }

    public void setCommentAuthorName(String authorname){
        this.commentAuthorName=authorname;
    }

    public String getCommentAuthorName(){
        return this.commentAuthorName;
    }
}
