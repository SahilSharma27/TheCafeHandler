package com.example.android.kartooncafehandler;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;

public class AddMenuActivity extends AppCompatActivity {

    Uri imageuri;
    ImageView imageView;
    DatabaseReference root;
    StorageReference reference;
    ProgressBar pb;
    EditText editText;
    Button addItem_btn;
    RecyclerView submenuRCV;
    public static ArrayList<Item> menuSubItems;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_menu);
        Button addBack = findViewById(R.id.add_back);
        Button savebutton = findViewById(R.id.save);
        editText = findViewById(R.id.editText);
        pb = findViewById(R.id.imgupldPB);
        imageView = findViewById(R.id.menu_item_backdrop);
        addItem_btn = findViewById(R.id.addsubitembtn);
        submenuRCV = findViewById(R.id.addsubmenuRCV);


        root = FirebaseDatabase.getInstance().getReference("Menu");
        reference = FirebaseStorage.getInstance().getReference();

        pb.setVisibility(View.INVISIBLE);

        addItem_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),AddItemActivity.class);
                startActivity(intent);
            }
        });



        addBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent,2);
            }
        });



        savebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(imageuri != null){
                    uploadToFirebase(imageuri);
                }
                else{
                    Toast.makeText(getApplicationContext(),"Pick image",Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    private void uploadToFirebase(Uri uri) {
        StorageReference ref = reference.child(System.currentTimeMillis() + "." + getFileExtension(uri));
        ref.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        MenuItem menuItem = new MenuItem();
                        menuItem.setTitle(editText.getText().toString());
                        menuItem.setBackdrop(uri.toString());
                        menuItem.setList(null);

                        String menuId = root.push().getKey();
                        root.child(menuId).setValue(menuItem);
                        pb.setVisibility(View.GONE);

                        Toast.makeText(getApplicationContext(),"Upload Successful",Toast.LENGTH_LONG).show();

                    }
                });
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                pb.setVisibility(View.VISIBLE);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(),"Failed",Toast.LENGTH_LONG).show();
                pb.setVisibility(View.INVISIBLE);

            }
        });
    }

    private String getFileExtension(Uri uri) {
        ContentResolver cr = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(cr.getType(uri));


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 2 && resultCode == RESULT_OK && data != null){
            imageuri = data.getData();
            imageView.setImageURI(imageuri);
            




        }
    }
}