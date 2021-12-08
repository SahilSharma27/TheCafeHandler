package com.example.android.kartooncafehandler;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.annotations.Nullable;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class UpdateActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<MenuItem> menuItems;
    OnMenuItemClickListener listener;
    MenuAdapter adapter;
    FloatingActionButton fab;
    private ChildEventListener mChildEventListener;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference menuDatabaseReference;
    private StorageReference menuImgRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        recyclerView = findViewById(R.id.menuView);
        fab =  findViewById(R.id.fabAdd);

        menuItems = new ArrayList<>();
        mFirebaseDatabase=FirebaseDatabase.getInstance();

        menuDatabaseReference=mFirebaseDatabase.getReference().child("Menu");
        attachDatabaseReadListener();



        adapter = new MenuAdapter(this, menuItems, new OnMenuItemClickListener() {
            @Override
            public void onMenuItemClick(View view, int pos) {
                Toast.makeText(getApplicationContext(),"OPEN" ,Toast.LENGTH_LONG).show();

            }
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //start add menu item activity
                Intent intent = new Intent(getApplicationContext(),AddMenuActivity.class);
                startActivity(intent);
            }
        });


    }

    private void attachDatabaseReadListener(){
        if(mChildEventListener == null) {
            mChildEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    MenuItem items = dataSnapshot.getValue(MenuItem.class);
                   // items.setMenuKey(dataSnapshot.getKey());
                    menuItems.add(items);
                    adapter.notifyDataSetChanged();
//                    orderNames.add(orders.getName());

                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) { }

                @Override
                public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) { }

                @Override
                public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) { }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) { }
            };
            menuDatabaseReference.addChildEventListener(mChildEventListener);
        }
    }
}