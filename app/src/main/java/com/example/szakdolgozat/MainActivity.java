package com.example.szakdolgozat;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.example.myapplication.R;

import java.io.File;
import java.net.Inet4Address;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static final int CAMERA_REQUEST_CODE = 1;
    public static final int VIDEO_REQUEST_CODE = 2;
    public static final int POST_CREATE_CODE = 3;

    String mediaSavePath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS).getAbsolutePath()
            + File.separator + "Szakdolgozat" + File.separator + "elso.mp4";


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent makePost = new Intent(MainActivity.this, makePost.class);
                startActivityForResult(makePost, POST_CREATE_CODE);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ListView commentListView = (ListView) findViewById(R.id.commentList);
        ArrayList<Post> commentArray = new ArrayList<>();
        media titleImage = new media(Uri.fromFile(new File(makePost.path + File.separator + "elso.png")));
        media titleVideo=new media(Uri.parse(makePost.path + File.separator + "elso.mp4"));


        Post comm = new Post(titleImage);

        comm.AddMediaToGrid(titleImage);
        comm.AddMediaToGrid(titleVideo);
        comm.AddMediaToGrid(titleImage);
        comm.AddMediaToGrid(titleVideo);
        comm.AddMediaToGrid(titleImage);
        comm.AddMediaToGrid(titleVideo);
        comm.AddMediaToGrid(titleImage);
        comm.AddMediaToGrid(titleVideo);

        Comment cc=new Comment();
        cc.setCommentText("Na végre muikodik");
        cc.setCommentAuthorName("Attila");
        comm.AddCommentToList(cc);
        comm.AddCommentToList(cc);
        comm.AddCommentToList(cc);
        comm.AddCommentToList(cc);

        comm.setTitle("Ez egy title");
        comm.setAuthorname("Én vagyok az author");
        commentArray.add(comm);
        commentArray.add(comm);



        commentListView.setAdapter(new PostListAdapter(this, commentArray));

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            Intent cameraIntent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(cameraIntent, CAMERA_REQUEST_CODE);
        } else if (id == R.id.nav_gallery) {
            Intent videoIntent=new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
            videoIntent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
            startActivityForResult(videoIntent, VIDEO_REQUEST_CODE);
        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(resultCode==RESULT_OK){
            switch(requestCode){
                case CAMERA_REQUEST_CODE:
                    Intent makepostIntent=new Intent(MainActivity.this, makePost.class);
                    makepostIntent.putExtras(data.getExtras());
                    makepostIntent.putExtra("content", "image");
                    startActivityForResult(makepostIntent, POST_CREATE_CODE);
                    break;
                case VIDEO_REQUEST_CODE:
                    Intent makeVideoPostIntent=new Intent(MainActivity.this, makePost.class);
                    makeVideoPostIntent.putExtra("content", "video");
                    makeVideoPostIntent.putExtra("videoUri", data.getData().toString());
                    startActivityForResult(makeVideoPostIntent, POST_CREATE_CODE);
                    break;
                case POST_CREATE_CODE:
                    Intent makePostBack=getIntent();
                    String testString=makePostBack.getStringExtra("test");
                    Toast.makeText(getApplicationContext(), testString, Toast.LENGTH_LONG).show();
                    break;
            }
        }
    }
}
