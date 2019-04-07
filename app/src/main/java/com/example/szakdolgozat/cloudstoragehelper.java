package com.example.szakdolgozat;

import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;

public class cloudstoragehelper {

    FirebaseStorage storage=null;
    StorageReference storeReference=null;


    public cloudstoragehelper(){
        storage=FirebaseStorage.getInstance();
        storeReference=storage.getReference();
    }

    public void mediaAdd(File uploadFile, String child, final Context cont){
        //Toast.makeText(cont, mediaSavePath, Toast.LENGTH_LONG).show();
        storeReference.child(child).putFile(Uri.fromFile(uploadFile)).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                Toast.makeText(cont, "Feltöltés kész", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void deleteMedia(String child, final Context cont){
        StorageReference refer=storeReference.child(child);
        refer.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(cont, "Törlés kész", Toast.LENGTH_LONG).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(cont, "Nem sikerült a törlés!"+"\n"+e.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
