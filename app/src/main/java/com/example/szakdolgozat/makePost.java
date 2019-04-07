package com.example.szakdolgozat;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.myapplication.R;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class makePost extends AppCompatActivity {

    Intent getContentIntent=null;

    private String videoFileName="elso";
    private String imageFileName="elso";
    public static String path= Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS).getAbsolutePath()+ File.separator+
            "Szakdolgozat";


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_post);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ArrayList<media> gridMediaContainer=new ArrayList<>();

        GridView postMediaContentGridView=(GridView)findViewById(R.id.addMediaGrid);

        try {
            getContentIntent = getIntent();
            if(getContentIntent.getStringExtra("content").equals("image")) {
                gridMediaContainer.add(saveImageFromIntent(this, getContentIntent));
            }else if(getContentIntent.getStringExtra("content").equals("video")) {
                Uri videoUri=getVideoUriFromIntent(this, getContentIntent);
                Uri gridVideoUri=saveMediaToFile("video/mp4",videoFileName, videoUri);
                gridMediaContainer.add(new media(gridVideoUri));
            }
        }catch (Exception f){
            f.printStackTrace();
        }

        postMediaContentGridView.setAdapter(new contentGridViewAdapter(getApplicationContext(), gridMediaContainer));

        Button addPostButton=(Button)findViewById(R.id.addPostButton);
        addPostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backMain=new Intent(makePost.this, MainActivity.class);
                backMain.putExtra("test", "test");
                setResult(RESULT_OK, backMain);
                finish();
            }
        });

        Button cancelPostButton=(Button)findViewById(R.id.cancelAddPostButton);
        cancelPostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private media saveImageFromIntent(Context context, Intent imageIntent){

        media returnMedia=null;
        File ff=new File(path);
        if(!ff.exists()){
            ff.mkdirs();
        }
        try {
            Bitmap bitmap=(Bitmap)imageIntent.getExtras().get("data");
            ByteArrayOutputStream out=new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
            File byteTempFile=new File(path+File.separator+imageFileName+".png");
            byteTempFile.createNewFile();
            returnMedia=new media(Uri.fromFile(byteTempFile)); //visszaadom a kép urijáát
            FileOutputStream outputStream=new FileOutputStream(byteTempFile);
            outputStream.write(out.toByteArray());
            outputStream.flush();
            outputStream.close();
        }catch (Exception f){
            f.printStackTrace();
        }
        return returnMedia;
    }

    private Uri getVideoUriFromIntent(Context context, Intent videoIntent){
        Uri returnUri=null;
        try {
            Uri videoUri=Uri.parse(videoIntent.getStringExtra("videoUri"));
            returnUri =videoUri;
        }catch (Exception g){
            g.printStackTrace();
        }
        return returnUri;
    }

    private Uri saveMediaToFile(String mimeTypeFilter, String filename, Uri source){

        File ff=new File(path);
        if(!ff.exists()){
            ff.mkdirs();
        }
        String mimeType=mimeTypeFilter.split("/")[1];
        File mediaFile=new File(ff.getAbsolutePath()+File.separator+filename+"."+mimeType);
        try {
            mediaFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ContentResolver resolver = this.getContentResolver();
        String[] mymeTypes = resolver.getStreamTypes(source, mimeTypeFilter);
        FileInputStream input = null;
        try {
            input = resolver.openTypedAssetFileDescriptor(source, mimeTypeFilter, null).createInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedInputStream inputStream = new BufferedInputStream(input);
            BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(mediaFile.getAbsolutePath(), false));
            byte[] byteBuffer = new byte[1024];
            inputStream.read(byteBuffer);
            do {
                outputStream.write(byteBuffer);
            } while (inputStream.read(byteBuffer) != -1);
            inputStream.close();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Uri.fromFile(mediaFile);
    }

}
