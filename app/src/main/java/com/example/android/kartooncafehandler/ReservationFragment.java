package com.example.android.kartooncafehandler;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;


public class ReservationFragment extends Fragment {
    RecyclerView recyclerView;
    ReservationAdapter adapter;
    View root;
    ArrayList<TableReservation> reservationList;
    private ChildEventListener mChildEventListener;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mOrdersDatabaseReference;



    public ReservationFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root= inflater.inflate(R.layout.fragment_reservation, container, false);
        reservationList=new ArrayList<>();
        recyclerView=root.findViewById(R.id.reservation_rcview);
        mFirebaseDatabase=FirebaseDatabase.getInstance();

        mOrdersDatabaseReference=mFirebaseDatabase.getReference().child("TableReservation");
        attachDatabaseReadListener();


        adapter = new ReservationAdapter(getContext(), reservationList, new ReservationClickListener() {
            @Override
            public void OnReservationItemClick(View view, int position) {
                TableReservation reservation=reservationList.get(position);
                Intent intent=new Intent(getContext(),ReservationDetailsActivity.class);
                intent.putExtra("selectedReservation",reservation);
                startActivity(intent);
            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setReverseLayout(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);


        return root;

    }
    private void attachDatabaseReadListener(){
        if(mChildEventListener == null) {
            mChildEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    TableReservation reservation = dataSnapshot.getValue(TableReservation.class);
                    reservation.setReservationKey(dataSnapshot.getKey());
                    reservationList.add(reservation);
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
            mOrdersDatabaseReference.addChildEventListener(mChildEventListener);
        }
    }


}
