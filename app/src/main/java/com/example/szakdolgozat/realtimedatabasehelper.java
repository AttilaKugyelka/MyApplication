package com.example.szakdolgozat;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class realtimedatabasehelper {

    DatabaseReference realtimeDatabase=null;

    public realtimedatabasehelper(){
        realtimeDatabase=FirebaseDatabase.getInstance().getReference().getRoot();
    }

    public void createPost(String child, Object values){
        realtimeDatabase.child(child).push().setValue(values);
    }

    String readString="";
    public void readPost(final String child, final Context cont) {
       realtimeDatabase.orderByChild(child).addChildEventListener(new ChildEventListener() {
           @Override
           public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
               for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                   readString+=snapshot.getValue(String.class)+": "+snapshot.getKey()+"\n";
                   Toast.makeText(cont, readString, Toast.LENGTH_LONG).show();
               }
           }

           @Override
           public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

           }

           @Override
           public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

           }

           @Override
           public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

           }

           @Override
           public void onCancelled(@NonNull DatabaseError databaseError) {
               Toast.makeText(cont, "Nem megy az adatbázis olvasás",Toast.LENGTH_LONG).show();
           }
       });
    }

    public void updatePost(final String child, final Object updateValue, final Context cont){
        realtimeDatabase.child(child).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot: dataSnapshot.getChildren()){
                    if(snapshot.getKey().equals(snapshot.getKey())){ //ide bekérni a módosítani kíváni key-t.
                        realtimeDatabase.child(child+"/"+snapshot.getKey()).setValue(updateValue);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(cont, "Nem megy az update",Toast.LENGTH_LONG).show();
            }
        });
    }

    public void deletePost(final String child, Object value, final Context cont){
        realtimeDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapsot:dataSnapshot.getChildren()){
                    snapsot.getRef().removeValue();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(cont, "Nem megy a törlés",Toast.LENGTH_LONG).show();
            }
        });
    }


}

