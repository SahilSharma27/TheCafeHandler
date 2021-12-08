package com.example.android.kartooncafehandler;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseMessaging.getInstance().subscribeToTopic("Orders");
        FirebaseMessaging.getInstance().subscribeToTopic("TableReservation");

        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        HomeFragment homeFragment=new HomeFragment();
        fragmentTransaction.replace(R.id.fragment_container,homeFragment);
        fragmentTransaction.commit();



        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.orders:
                        FragmentManager fragmentManager=getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                        OrderFragment orderFragment=new OrderFragment();
                        fragmentTransaction.replace(R.id.fragment_container,orderFragment);
                        fragmentTransaction.commit();
                        break;
                    case R.id.table_reservation:
                        FragmentManager fragmentManager1=getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction1=fragmentManager1.beginTransaction();
                        ReservationFragment Fragment=new ReservationFragment();
                        fragmentTransaction1.replace(R.id.fragment_container,Fragment);
                        fragmentTransaction1.commit();
                        break;
                }
                return true;
            }
        });

    }
}
