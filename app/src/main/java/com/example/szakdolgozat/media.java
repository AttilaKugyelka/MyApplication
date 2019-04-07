package com.example.szakdolgozat;
import android.graphics.Bitmap;
import android.net.Uri;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.VideoView;

public class media {

    private ImageView image=null;
    private VideoView video=null;
    private Bitmap imageBitmap=null;
    private Uri mediaUri =null;
    private int resourceImageLocal=0;

    public media(ImageView tempImageView, VideoView tempVideoView){
        image=tempImageView;
        video=tempVideoView;
    }

    public media(int resourceImage){
        resourceImageLocal=resourceImage;
    }

    public media(Bitmap bitmap){
        imageBitmap=bitmap;
    }

    public media(Uri tempVideoUri){
        mediaUri =tempVideoUri;
    }

    public Bitmap getBitmapImage(){return imageBitmap;}

    public int getResourceImage(){
        return resourceImageLocal;
    }

    public ImageView getImage() {
        return image;
    }

    public VideoView getVideo(){
        return video;
    }

    public Uri getMediaUri(){return mediaUri;}

    public boolean getVideoOrImage(){

        if(mediaUri.toString().contains(".mp4")){
            return true;
        }
        return false; //Ha false akkor videonak nyilvánítja
    }
}
